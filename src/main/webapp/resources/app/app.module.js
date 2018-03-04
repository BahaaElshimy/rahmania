(function () {
    var rahmania = angular.module('rahmania', ['ngSanitize', 'ngRoute', 'ngResource', 'ngPatternRestrict', 'selectize']);

    rahmania.run(function ($rootScope, rahmaniaService, $location, $timeout) {
        $rootScope.arabicOnly = '';

        $rootScope.authenticated = false;
        $rootScope.mobileNumber = '/[0][5][0-9]{8}$/';

        // TODO password regex
        $rootScope.menueSet = [];
        $rootScope.retriveMenu = function () {
            rahmaniaService.retriveMenu().success(function (data) {
                $rootScope.menueSet = data;
            });
        }
        $rootScope.retriveMenu();


        $rootScope.bad = function (value) {
            if (value == undefined || value == null || (angular.isString(value) && value.trim() === ""))
                return true;
            return false;
        };

        $rootScope.getUser = function () {
            rahmaniaService.getUserData().success(function (data) {
                $rootScope.user = data;
                $rootScope.authenticated = true;
                $rootScope.routeBasedUser();
            });

        }
        $rootScope.getUser();

        $rootScope.routeBasedUser = function () {
            $rootScope.retriveMenu();
            if ($rootScope.user.role == "admin") {
                $location.path("/editSubjects")
            } else {
                $location.path("/test")
            }
        }
        $rootScope.isLoggedIn = function () {
            if (!$rootScope.bad($rootScope.user)) {
                return true;
            }
            return false;
        }


        //TODO reload
        $rootScope.logout = function () {
            rahmaniaService.logout().success(function () {
                $rootScope.authenticated = false;
                $rootScope.retriveMenu();
                $location.path("/login");
            });
        }

    });

    rahmania.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('requestResponseInterceptor');
    }]);

    rahmania.config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'resources/html/index.html',
            })
            .when('/about', {
                templateUrl: 'resources/html/aboutUs.html',
                controller: function (rahmaniaService, $scope) {
                    rahmaniaService.getAbout().success(function (data) {
                        $scope.about = data;
                    });
                }
            })
            .when('/questions/:id', {
                templateUrl: '/resources/adminHtml/subjectQuestions.html',
                controller: "questionController",
            })
            .when('/editSubjects', {
                templateUrl: 'resources/adminHtml/tabs.html',
                controller: 'subjectController',
            })
            .when('/login', {
                templateUrl: 'resources/html/login.html',
                controller: 'loginController',
            })

            .when('/register', {
                templateUrl: 'resources/html/register.html',
                controller: 'registerController',
            })
            .when('/confirm', {
                templateUrl: 'resources/html/confirm.html',
                controller: 'confirmController',
            })
            .when('/check', {
                templateUrl: 'resources/html/check.html',
                controller: 'tokenController',
            })
            .when('/registred', {
                templateUrl: 'resources/html/registred.html',
            })
            .when('/test', {
                templateUrl: 'resources/html/test.html',
                controller: 'testController'
            })
            .when('/agreement', {
                templateUrl: 'resources/html/agreement.html',
                controller: 'agreementController'
            })
            .when('/prizes', {
                templateUrl: 'resources/html/prizes.html',
                controller: function (rahmaniaService, $scope) {
                    rahmaniaService.getPrizes().success(function (data) {
                        $scope.prizes = data;
                    });
                }
            })
            .when('/subjects', {
                templateUrl: 'resources/html/studentSubjects.html',
                controller: 'studentSubjectController'
            })
            .when('/correct', {
                templateUrl: 'resources/html/reponses.html',
                controller: 'correctionController'
            }).when('/studentAnswers/:id', {
            templateUrl: 'resources/html/studentAnswers.html',
            controller: 'studnetAnswerController'
        }).when('/editConstraint', {
            templateUrl: 'resources/adminHtml/editConstraint.html',
            controller: 'constraintController'
        })
            .when('/editAbout', {
                templateUrl: 'resources/adminHtml/editAbout.html',
                controller: 'aboutController'
            })
            .when('/editPrizes', {
                templateUrl: 'resources/adminHtml/editPrizes.html',
                controller: 'prizesController'
            }).when('/constraints', {
            templateUrl: 'resources/html/rules.html',
            controller: function (rahmaniaService, $scope) {
                rahmaniaService.getRules().success(function (data) {
                    $scope.rules = data;
                });
            }
        })

            .otherwise(
                {redirectTo: '/'}
            );
    });


    rahmania.factory('requestResponseInterceptor', ['$q', '$location', function ($q, $location) {
        var responseInterceptor = {
            response: function (response) {
                var deferred = $q.defer();

                if (response.status == 401) {
                    alert("Not Authorized");
                }
                return response;
            },

            'responseError': function (rejection) {
                // do something on error
                if (rejection.status === 401) {
                    $location.path('/login');
                }
                return $q.reject(rejection);
            }
        };

        return responseInterceptor;
    }]);

    rahmania.controller('subjectController', ['$scope', 'rahmaniaService', '$http', function ($scope, rahmaniaService, $http) {

        $scope.isAdd = true;
        $scope.loadSubjects = function () {
            rahmaniaService.getAllSubject().success(function (data) {
                $scope.subjects = data;
            });

        };
        $scope.loadSubjects();


        $scope.addSubject = function () {
            rahmaniaService.addSubject($scope.subject, $scope.file).success(function () {
                $scope.loadSubjects();
                $scope.resetSubject();
            }).error(function () {
                alert(" error");
            });
        };


        var subjectForm = $('#addsubject').parsley();
        $('#addsubject').parsley().on('form:validated', function () {
            if (subjectForm.isValid()) {
                if ($scope.isAdd) {
                    $scope.addSubject();
                } else {
                    $scope.editSubject();
                }
            }
        });

        $scope.deleteFile = function () {
            rahmaniaService.deleteSubjectVideo($scope.subject.id).success(function (data) {
                $scope.subject.videoPath = "";
            });
        }

        $scope.editSubject = function () {
            rahmaniaService.eidtSubject($scope.subject).success(function () {
                $scope.loadSubjects();
                $scope.resetSubject();
            }).error(function () {
                alert(" error");
            });
        };


        $scope.prepareEditSubject = function ($index) {
            $scope.isAdd = false;
            $scope.subject = angular.copy($scope.subjects[$index]);
        };


        $scope.deleteSubject = function (id) {
            rahmaniaService.deleteSubject(id).success(function () {
                $scope.loadSubjects();
                $scope.resetSubject();
            }).error(function (data) {
                alert(data);
            });
        };

        $scope.resetSubject = function () {
            $scope.subject = {};
            $("#subjectVideoUploader").val('');
            $scope.isAdd = true;
        }

    }]);


    rahmania.controller('loginController', ['$rootScope', '$scope', 'rahmaniaService', '$location', function ($rootScope, $scope, rahmaniaService, $location) {
        $scope.errorMessage = "";
        $scope.login = function () {
            rahmaniaService.login({
                username: $scope.username,
                password: $scope.password
            }).success(function () {
                rahmaniaService.getUserData().success(function (data) {
                    $rootScope.authenticated = true;
                    $rootScope.user = data;
                    $rootScope.routeBasedUser();
                })
            }).error(function (data) {
                $scope.errorMessage = "رقم الموبايل / كلمة المرور خطأ",
                    $scope.password = "";
            });
        };


        var loginForm = $('#loginForm').parsley();
        $('#loginForm').parsley().on('form:validated', function () {

            if (loginForm.isValid()) {
                $scope.login();
            }
        });

    }]);


    rahmania.controller('registerController', ['$rootScope', '$scope', 'rahmaniaService', '$location', function ($rootScope, $scope, rahmaniaService, $location) {

        $scope.userExist = false;
        $scope.isExist = function () {
            rahmaniaService.isExist($scope.req.mobileNumber).success(function (data) {
                if (data) {
                    $scope.userExist = true;
                } else {
                    $scope.userExist = false;
                }
            });
        }

        $scope.register = function () {
            rahmaniaService.register($scope.req).success(function () {
                $rootScope.mobileNumber = $scope.req.mobileNumber;
                $location.path("/confirm");
            }).error(function (data) {
                console.log(data);
            });
        };

        var registerForm = $('#registerForm').parsley();
        $('#registerForm').parsley().on('form:validated', function () {
            if (registerForm.isValid()) {
                if ($scope.req.password != $scope.req.confirmpassword)
                    return;
                $scope.register();
            }
        });


    }]);

    rahmania.controller('tokenController', ['$rootScope', '$scope', 'rahmaniaService', '$location', function ($rootScope, $scope, rahmaniaService, $location) {


        var validateTokenForm = $('#validateTokenForm').parsley();
        $('#validateTokenForm').parsley().on('form:validated', function () {
            if (validateTokenForm.isValid()) {
                $scope.validateToken();
            }
        });

        $scope.invalidTokenMessage ="";
        $scope.validateToken = function () {
            $scope.invalidTokenMessage ="";
            rahmaniaService.validateToken($scope.token).success(function (data) {
                if(data.replyCode ==0){
                    $location.path("/agreement");
                }else{
                    $scope.invalidTokenMessage ="الكودالمدخل غير صحيح من فضلك اعد المحاولة";
                }
            }).error(function (data) {
                console.log(data);
            });
        };


    }]);


    rahmania.controller('confirmController', function () {

    });

    rahmania.controller('testController', ['$rootScope', '$scope', 'rahmaniaService', '$q', '$timeout' ,'$window', function ($rootScope, $scope, rahmaniaService, $q, $timeout ,$window) {
        $scope.currentSubject = 0;
        $scope.subjects = [];
        $scope.questions = [];
        $scope.videoSource = undefined;
        $scope.prepareTestPage = function () {
            $q.all([rahmaniaService.getAllSubject(), rahmaniaService.getAllStudentQuestions()]).then(function (values) {
                $scope.subjects = values[0].data;
                $scope.questions = values[1].data;
                $scope.videoSource = 'resources/video/' + $scope.subjects[$scope.currentSubject].id + '/' + $scope.subjects[$scope.currentSubject].videoName;
            });
        };
        $scope.prepareTestPage();


        $scope.setSubject = function (index) {
            $scope.currentSubject = index;
            $scope.videoSource = 'resources/video/' + $scope.subjects[$scope.currentSubject].id + '/' + $scope.subjects[$scope.currentSubject].videoName;
        };


        var testForm = $('#testForm').parsley();
        $('#testForm').parsley().on('form:validated', function () {

            console.log(testForm.fields);
            if (testForm.isValid()) {

            } else {
                goErroStep(testForm.fields)
                return;
            }
        });


        function goErroStep(fields) {
            for (i in fields) {
                if (typeof fields[i].validationResult != 'boolean' && fields[i].validationResult.length) {

                    $timeout(function () {
                        $scope.setSubject(getSubjectIndex(parseInt(fields[i].element.id.split("-")[1])));

                    }, 200);
                    return;
                }
            }
        }


        $scope.submitAnswers = function () {
            // if ($scope.checkOptions()) {
            if ($scope.testForm.$valid) {
                $("#submittModal").modal();
                $("#submitBtn").on("click", function () {

                    rahmaniaService.submitAnswers($scope.questions).success(function () {
                        $("#sentScuccess").modal();
                    });
                    $("#submittModal").modal('hide');
                });

            } else {
                $("#submittAllAnswers").modal();
            }
        }

        $scope.reloadPage = function(){
            $window.location.reload();
        }


        function getSubjectIndex(subjectId) {
            for (var i = 0; i < $scope.subjects.length; i++) {
                if ($scope.subjects[i].id == subjectId) {
                    return i;
                }
            }
        }

        $scope.next = function () {
            if ($scope.currentSubject < $scope.subjects.length - 1) {
                $scope.currentSubject++;
                $scope.videoSource = 'resources/video/' + $scope.subjects[$scope.currentSubject].id + '/' + $scope.subjects[$scope.currentSubject].videoName;
            }

        };

        $scope.previous = function () {
            if ($scope.currentSubject > 0) {
                $scope.currentSubject--;
                $scope.videoSource = 'resources/video/' + $scope.subjects[$scope.currentSubject].id + '/' + $scope.subjects[$scope.currentSubject].videoName;
            }
        };

        $scope.tempSave = function () {
            $("#saveModal").modal('hide');
            rahmaniaService.saveStudentAnswers($scope.questions).success(function (data) {
                $("#sentScuccess").modal();
            });
        };

        $scope.openModal = function () {
            $("#saveModal").modal();
        }

    }]);


    rahmania.controller('agreementController', ['$scope', 'rahmaniaService', '$location', '$rootScope', function ($scope, rahmaniaService, $location, $rootScope) {
        $scope.prepareTestPage = function () {
            $rootScope.getUser();
        }
    }]);
    rahmania.controller('studentSubjectController', ['$scope', 'rahmaniaService', function ($scope, rahmaniaService) {
        rahmaniaService.getAllSubject().success(function (data) {
            $scope.studentSubjects = data;
        })
    }]);
    rahmania.controller('questionController', ['$scope', 'rahmaniaService', '$routeParams', '$compile', '$rootScope', function ($scope, rahmaniaService, $routeParams, compile, $rootScope) {

        var subjectId = $routeParams.id;
        $scope.isAddQuestion = true;

        // quetion types

        $scope.quetionOptions = [
            {id: "multichoice", name: 'اختر من متعدد'},
            {id: "fillGap", name: 'اكمل الفراغات'},
            {id: "wQuestion", name: 'اجابة كاملة'}
        ];
        $scope.questionConfig = {
            maxItems: 1,
            valueField: 'id',
            labelField: 'name',
            searchField: ['name']
        }
        // questions


        $scope.loadQuestions = function () {
            rahmaniaService.getSubjectQuestions(subjectId).success(function (data) {
                $scope.questions = data;
            });

        };
        $scope.resetQuestion = function () {
            $scope.question = {question: "", answers: []};
            $scope.isAddQuestion = true;
        };

        $scope.loadQuestions();
        $scope.resetQuestion();

        $scope.addQuestion = function () {
            rahmaniaService.addQuestion($scope.type, $scope.question, subjectId).success(function () {
                $scope.loadQuestions();
                $scope.resetQuestion();
            }).error(function () {
                alert(" error");
            });
        };

        $scope.resetCorrectAnswer = function (x) {
            for (var i = 0; i < $scope.question.answers.length; i++) {

                if (!$rootScope.bad(x) && i == x) {
                    continue;
                }

                $scope.question.answers[i].correct = false;
            }
        };


        $scope.addAnswer = function () {
            if ($scope.tempAnswer.correct) {
                $scope.resetCorrectAnswer(undefined);
            }
            $scope.question.answers.push(angular.copy($scope.tempAnswer));
            $scope.resetTempAnswer();

        }

        $scope.removeAnswer = function (index) {
            $scope.question.answers.splice(index, 1);
        }


        var testQuestion = $('#testQuestion').parsley();
        $('#testQuestion').parsley().on('form:validated', function () {

            if (testQuestion.isValid()) {
                $scope.addQuestion();
            }
        });

        $scope.editQuestion = function () {
            rahmaniaService.eidtQuestion($scope.question).success(function () {
                $scope.loadQuestions();
                $scope.resetQuestion();
            }).error(function () {
                alert(" error");
            });
        };


        $scope.prepareEditQuestion = function (index) {
            $scope.isAddQuestion = false;
            if ($rootScope.bad($scope.question.answers) || $scope.question.answers.length < 1)
                $scope.type = "fillGap";
            else
                $scope.type = "multichoice";

            $scope.question = angular.copy($scope.questions[index]);
        };


        $scope.removeQuestion = function (id) {
            rahmaniaService.deleteQuestion(id).success(function () {
                $scope.loadQuestions();
                $scope.resetQuestion();
            }).error(function (data) {
                alert(data);
            });
        };


        $scope.resetTempAnswer = function () {
            $scope.tempAnswer = {correct: false, answer: ""};
        }

        $scope.resetTempAnswer();


    }]);


    rahmania.controller('correctionController', ['$scope', 'rahmaniaService', '$routeParams', '$compile', '$rootScope', function ($scope, rahmaniaService, $routeParams, compile, $rootScope) {
        $scope.selectedStudents = [];
        $scope.setAll = false;
        $scope.studentsCheckBoxes = [];

        $scope.loadStudents = function () {
            rahmaniaService.getAllStudents().success(function (data) {
                $scope.studentsCheckBoxes = [];
                $scope.students = data;
                for (var i = 0; i < $scope.students.length; i++) {
                    $scope.studentsCheckBoxes[i] = false;
                }
            });

        }

        $scope.loadStudents();

        $scope.addStudentId = function (index) {
            if ($scope.studentsCheckBoxes[index])
                $scope.selectedStudents.push($scope.students[index].id);
            else {
                var selectIndex = $scope.selectedStudents.indexOf($scope.students[index].id)
                $scope.selectedStudents.splice(selectIndex, 1);
            }

        };

        $scope.checkAllStudents = function () {
            $scope.setAll = (!$scope.setAll);
            if (!$scope.setAll) {
                $scope.selectedStudents = [];
            }
            for (var i = 0; i < $scope.studentsCheckBoxes.length; i++) {
                $scope.studentsCheckBoxes[i] = $scope.setAll;
            }

        };

        $scope.getPartcipated = function () {
            rahmaniaService.getParticpated().success(function (data) {
                $scope.studentsCheckBoxes = [];
                $scope.students = data;
                for (var i = 0; i < $scope.students.length; i++) {
                    $scope.studentsCheckBoxes[i] = false;
                }
            });

        };
        $scope.getCandidate = function () {
            rahmaniaService.getCandidate().success(function (data) {
                $scope.studentsCheckBoxes = [];
                $scope.students = data;
                for (var i = 0; i < $scope.students.length; i++) {
                    $scope.studentsCheckBoxes[i] = false;
                }
            });

        };

        $scope.greaterThan = function (prop, val) {
            return function (item) {
                if ($rootScope.bad(val)) {
                    return item[prop];
                }

                return item[prop] >= val;
            }
        };

        $scope.moveToParticipated = function () {
            if ($scope.selectedStudents.length < 1 && !$scope.setAll) {
                $("#chooseStudent").modal();
                return;
            }
            rahmaniaService.moveToParticipated($scope.selectedStudents, $scope.setAll).success(function (data) {
                $("#particpateModal").modal();
            });
        }

        $scope.elimenate = function () {
            if ($scope.selectedStudents.length < 1) {
                $("#elimnateModal").modal();
                return;
            }
            rahmaniaService.elimenate($scope.selectedStudents).success(function (data) {
                $("#chooseStudent").modal();

            });
        };

        $scope.endTest = function (){
            rahmaniaService.endTest().success(function (data) {
                $("#endTest").modal();

            });
        };

        $scope.moveToCandidate = function (){
            if ($scope.selectedStudents.length < 1 && !$scope.setAll) {
                $("#chooseStudent").modal();
                return;
            }
            rahmaniaService.moveToCandidate($scope.selectedStudents, $scope.setAll).success(function (data) {
                $("#particpateModal").modal();
            });
        };

    }]);


    rahmania.controller('studnetAnswerController', ['$scope', 'rahmaniaService', '$rootScope', '$routeParams', '$location', '$timeout', function ($scope, rahmaniaService, $rootScope, $routeParams, $location, $timeout) {
        var studentId = $routeParams.id;

        $scope.loadStudentAnswers = function () {
            rahmaniaService.retriveStudentAnswers(studentId).success(function (data) {
                $scope.questions = data;
            });
        };
        $scope.getQuestionAnswer = function (question, id) {
            for (var i = 0; i < question.answers.length; i++) {
                if (question.answers[i].id == id)
                    return question.answers[i].answer;
            }
            return "";
        };

        $scope.loadStudentAnswers();

        $scope.moveToParticipated = function () {
            var ids = [studentId];
            rahmaniaService.moveToParticipated(ids).success(function () {
                $("#particpateModal").modal();
            });

        }

        $scope.elimenate = function () {
            var ids = [studentId];
            rahmaniaService.elimenate(ids).success(function () {
                $("#elimnateModal").modal();
            });
        }
        $scope.goToCorrection = function (name) {
            $('#' + name).modal('hide');
            $timeout(function () {
                $location.path("/correct")
            }, 1000);
        };


    }]);

    rahmania.controller('prizesController', ['$scope', 'rahmaniaService', '$rootScope', '$http', function ($scope, rahmaniaService, $rootScope, $http) {
        $scope.isAddPrize = true;
        $scope.doUploadImage = function () {
            var data = new FormData();
            data.append('file', $scope.file);
            var config = {
                transformRequest: angular.identity,
                transformResponse: angular.identity,
                headers: {
                    'Content-Type': undefined
                }
            }
            $http.post('api/settings/uploadPrizeImage', data, config)
        }

        $scope.addPrize = function () {
            rahmaniaService.addPrize($scope.prize).success(function (data) {
                $scope.resetPrize();
                $scope.loadPrizes();
            });

        };

        $scope.loadPrizes = function () {
            rahmaniaService.getPrizes().success(function (data) {
                $scope.prizes = data;
            });
        }

        $scope.editPrize = function () {
            rahmaniaService.editPrize($scope.prize).success(function (data) {
                $scope.resetPrize();
                $scope.loadPrizes();
            });
        };
        $scope.deletePrize = function (id) {
            rahmaniaService.deletePrize(id).success(function (data) {
                $scope.loadPrizes();
            });

        };

        $scope.prepareEditPrize = function ($index) {
            $scope.isAddPrize = false;
            $scope.prize = angular.copy($scope.prizes[$index]);
        };

        $scope.resetPrize = function () {
            $scope.prize = {};
            $scope.isAddPrize = true;
        };

        $scope.loadPrizes();

    }]);
    rahmania.controller('constraintController', ['$scope', 'rahmaniaService', '$rootScope', function ($scope, rahmaniaService, $rootScope) {

        $scope.isAddRule = true;

        $scope.addRule = function () {
            rahmaniaService.addRule($scope.rule).success(function (data) {
                $scope.resetRule();
                $scope.loadRules();
            });

        };

        $scope.loadRules = function () {
            rahmaniaService.getRules().success(function (data) {
                $scope.rules = data;
            });
        }

        $scope.editRule = function () {
            rahmaniaService.editRule($scope.rule).success(function (data) {
                $scope.resetRule();
                $scope.loadRules();
            });
        };
        $scope.deleteRule = function (id) {
            rahmaniaService.deleteRule(id).success(function (data) {
                $scope.loadRules();
            });

        };

        $scope.prepareEditRule = function ($index) {
            $scope.isAddRule = false;
            $scope.rule = angular.copy($scope.rules[$index]);
        };

        $scope.resetRule = function () {
            $scope.rule = {};
            $scope.isAddRule = true;
        };

        $scope.loadRules();


    }]);
    rahmania.controller('aboutController', ['$scope', 'rahmaniaService', '$rootScope', function ($scope, rahmaniaService, $rootScope) {
        $scope.addAbout = function () {
            rahmaniaService.addAbout($scope.about).success(function (data) {
                $scope.loadAbouts();
            });
        };
        $scope.loadAbouts = function () {
            rahmaniaService.getAbout().success(function (data) {
                $scope.about = data;
            });
        }

        $scope.editAbout = function () {
            rahmaniaService.editAbout($scope.about).success(function (data) {
                $scope.loadAbouts();
            });
        };

        $scope.loadAbouts();


    }]);


})();
