/**
 * Created by bahaa on 03/02/18.
 */
(function () {
    angular.module('rahmania').directive("displayMulti", function ($rootScope) {
        return {
            restrict: "E",
            templateUrl: "resources/adminHtml/displaymultichoiseQuestion.html",
            scope:false,

            controller: function ($scope, $rootScope) {

            }
        }
    });


    angular.module('rahmania').directive("whatQuestion", function ($rootScope) {
        return {
            restrict: "E",
            templateUrl: "resources/adminHtml/displayWhatQuestion.html",
            scope:false,

            controller: function ($scope, $rootScope) {

            }
        }
    });


    angular.module('rahmania').directive("displayFill", function ($rootScope  ,rahmaniaService ,$timeout ,$compile) {
        return {
            restrict: "E",
            template: '<div id="fillGap"></div>',
            scope: false,
            controller: function ($scope, $rootScope) {

            },
            compile: function (tElem, tAttrs) {
                return {
                    pre: function (scope, iElem, iAttrs) {
                        if(!$rootScope.bad(scope.item.question)){
                            var tempQuestion = "";
                            var newElement ="";
                            scope.item.parts =[];
                            for (var i = 0; i < scope.item.question.length; i++) {
                                if (scope.item.question[i] == "+") {
                                    newElement+="<span>" + tempQuestion + "</span>";
                                    newElement+="<input type='text'name='gap"+i+"'   style='padding:5px;width: 70px' />";
                                    tempQuestion = "";
                                } else {
                                    tempQuestion += scope.item.question[i];
                                }
                            }
                            newElement+="<span>" + tempQuestion + "</span>";
                            iElem.append($compile(newElement)(scope));

                            iElem.append($compile('<a id="fillEdit">تعديل</a> <span></span> <a id="fillRemove"> حذف</a><br/>')(scope))


                        }
                    },
                    post: function (scope, iElem, iAttrs) {
                        $timeout(function () {
                            iElem.on('click', '#fillEdit', function () {
                                $timeout(function (){
                                    scope.prepareEditQuestion(scope.$index)
                                },100);
                            });
                            iElem.on('click', '#fillRemove', function () {
                                scope.removeQuestion(scope.item.id);
                            })
                        });
                    }
                }
            },
            link: function (scope, element) {


            }
        }
    });

    angular.module('rahmania').directive("displayGap", function ($rootScope  ,rahmaniaService ,$timeout ,$compile) {
        return {
            restrict: "E",
            template: '',
            replace:true,
            scope: {
                item:'=',
                ngModel: "="
            },
            compile: function (tElem, tAttrs) {
                return {
                    pre: function (scope, iElem, iAttrs  ) {

                    },
                    post: function (scope, iElem, iAttrs) {
                        if(!$rootScope.bad(scope.item.question)){
                            var tempQuestion = "";
                            var displayEmelent = '<div class="container-fluid" > <div class="row">';
                            scope.item.parts =$rootScope.bad(scope.item.parts) ? [] : scope.item.parts;
                            var partCount  = 0;
                            for (var i = 0; i < scope.item.question.length; i++) {
                                if (scope.item.question[i] == "+") {

                                    displayEmelent+= !$rootScope.bad(tempQuestion) ?' <div   style="float: right"><span>'+ tempQuestion + "</span></div>" :"";

                                    displayEmelent+="<div  class='co-l-md-2' style='float: right;margin-bottom:5px;margin-right:5px'><input name='gap"+i+"' ng-model='item.parts["+partCount+"]'"+"   id='gap-"+scope.item.subjectId+"'   data-parsley-error-message='هذ الحقل مطلوب'  ng-required='true' style='padding:5px ;width: 250px' /></div>";
                                    tempQuestion = "";
                                    partCount++;
                                } else {
                                    tempQuestion += scope.item.question[i];
                                }
                            }
                            displayEmelent+= !$rootScope.bad(tempQuestion) ?' <div  style="float: right"><span>'+ tempQuestion + "</span></div></div></div>'" :"</div></div>'";
                            iElem.append($compile(displayEmelent)(scope));

                        }
                    }
                }
            },
            link:  function(scope, elem, attrs, ctrl){

            }
        }
    });

    angular.module('rahmania').directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function(){
                    scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);


    angular.module('rahmania').directive("subjectVideo", function ($rootScope) {
        return {
            restrict: "E",
            template: ' <video style="width: 50% !important;" controls id="myVideo"> <source   id="tv_main_channel" src="{{videoSource}}" type="video/mp4"  > </video> ',
            scope:{
                videoSource:'='
            },
             link:function( scope,elem ,attr){
                 scope.$watch('videoSource', function(value) {
                     document.getElementById("myVideo").load();
                 });
             },
            controller: function ($scope, $rootScope) {

            }
        }
    });

    angular.module('rahmania').directive('myEnter', function () {
        return function (scope, element, attrs) {
            element.bind("keydown keypress", function (event) {
                if(event.which === 13) {
                    scope.$apply(function (){
                        scope.$eval(attrs.myEnter);
                    });

                    event.preventDefault();
                }
            });
        };
    })

    angular.module('rahmania').directive("displayGapAnswer", function ($rootScope  ,rahmaniaService ,$timeout ,$compile) {
        return {
            restrict: "E",
            template: '',
            replace:true,
            scope: {
                item:'=',
                ngModel: "="
            },
            compile: function (tElem, tAttrs) {
                return {
                    pre: function (scope, iElem, iAttrs  ) {

                    },
                    post: function (scope, iElem, iAttrs) {
                        if(!$rootScope.bad(scope.item.question)){
                            var tempQuestion = "";
                            var newElement ="";
                            scope.item.parts =$rootScope.bad(scope.item.parts) ? [] : scope.item.parts;
                            var partCount  = 0;
                            for (var i = 0; i < scope.item.question.length; i++) {
                                if (scope.item.question[i] == "+") {
                                    iElem.append($compile("<span>" + tempQuestion + "</span>")(scope));
                                    newElement="<span>&nbsp; &nbsp; </span><input name='gap"+i+"' ng-model='item.parts["+partCount+"]'"+" ng-required='true' style='padding:5px;width: 150px' /> <span>&nbsp; &nbsp; </span>";
                                    iElem.append($compile(newElement)(scope));
                                    tempQuestion = "";
                                    partCount++;
                                } else {
                                    tempQuestion += scope.item.question[i];
                                }
                            }
                            iElem.append($compile("<span>" + tempQuestion + "</span>")(scope));


                        }
                    }
                }
            },
            link:  function(scope, elem, attrs, ctrl){

            }
        }
    });

})();
