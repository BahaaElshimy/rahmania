(function () {

    var serverUrl = 'localhost:8080/';
    angular.module('rahmania').service('rahmaniaService', ['$http', function ($http) {
        return {
            deleteSubject: function (id) {
                return $http({
                    url: 'api/subjects/' + id,
                    method: 'DELETE',
                    headers: 'Accept:application/json',
                });
            },
            deleteSubjectVideo: function (id) {
                return $http({
                    url: 'api/subjects/video/' + id,
                    method: 'DELETE',
                    headers: 'Accept:application/json',
                });
            },
            eidtSubject: function (subject , file) {
                var data = new FormData();
                data.append('file', file);
                data.append("subject" ,  angular.toJson(subject));
                var config = {
                    transformRequest: angular.identity,
                    transformResponse: angular.identity,
                    headers : {
                        'Content-Type': undefined
                    }
                }
                return $http.put("api/subjects/" + subject.id, data, config);
            },
            addSubject: function (subject , file) {
                var data = new FormData();
                data.append('file', file);
                data.append("subject" ,  angular.toJson(subject));
                var config = {
                    transformRequest: angular.identity,
                    transformResponse: angular.identity,
                    headers : {
                        'Content-Type': undefined
                    }
                }

               return $http.post('api/subjects', data, config);

            },
            getSubject: function (id) {
                return $http.get(serverUrl + "api/subjects/" + id);
            },
            getAllSubject: function () {
                return $http({
                    url: 'api/subjects',
                    method: 'GET',
                    headers: 'Accept:application/json'
                });
            },
            getAllQuestions: function () {
                return $http({
                    url: 'api/questions/',
                    method: 'GET',
                    headers: 'Accept:application/json'
                });
            },
            getAllStudentQuestions: function () {
                return $http({
                    url: 'api/questions/test',
                    method: 'GET',
                    headers: 'Accept:application/json'
                });
            },
            getQuestion: function (id) {
                return $http.get(serverUrl + "api/questions/" + id);
            },
            addQuestion: function ( type,question ,tabId) {
                return $http({
                    url: 'api/questions/'+type+'/'+tabId,
                    method: 'POST',
                    headers: 'Accept:application/json',
                    data: question
                });
            },
            deleteQuestion: function (id) {
                return $http({
                    url: 'api/questions/' + id,
                    method: 'DELETE',
                    headers: 'Accept:application/json',
                });
            },
            eidtQuestion: function (question) {
                return $http.put("api/questions/multichoice/" + question.id, question);
            },

            login: function (request) {
                return $http.post('login', $.param(request), {
                    headers: {
                        "content-type": "application/x-www-form-urlencoded"
                    }
                })
            },

            register: function (request) {
                return $http({
                    url: 'register',
                    method: 'POST',
                    headers: 'Accept:application/json',
                    data: request
                });
            },
            getUserData : function () {
                return $http({
                    url: 'api/users/current',
                    method: 'GET',
                    headers: 'Accept:application/json'
                });
            },
            logout :function () {
                return $http.post('logout' ,{
                    headers: {
                        "content-type": "application/x-www-form-urlencoded"
                    }
                })
            },
            retriveMenu: function () {
                return $http({
                    url: 'menues',
                    method: 'GET',
                    headers: 'Accept:application/json'
                });
            },
            getSubjectQuestions: function (id) {
                return $http({
                    url: 'api/subjects/questions/'+id,
                    method: 'GET',
                    headers: 'Accept:application/json'
                });
            },
            submitAnswers : function (request) {
                return $http({
                    url: 'api/studnet',
                    method: 'POST',
                    headers: 'Accept:application/json',
                    data: request
                });
            },
            loadExam:function () {
                return $http({
                    url: 'api/studnet',
                    method: 'GET',
                    headers: 'Accept:application/json',
                });
            },
            getAllStudents:function () {
                return $http({
                    url: 'api/studnet/listAll',
                    method: 'GET',
                    headers: 'Accept:application/json',
                });
            },
            getParticpated : function () {
                return $http({
                    url: 'api/studnet/partcipated',
                    method: 'GET',
                    headers: 'Accept:application/json',
                });
            },
            getAllQuestions: function () {
                return $http({
                    url: 'api/questions/',
                    method: 'GET',
                    headers: 'Accept:application/json'
                });
            },

            getAbout: function () {
                return $http.get("/about" );
            },
            addAbout: function (about) {
                return $http({
                    url: 'api/settings/about',
                    method: 'POST',
                    headers: 'Accept:application/json',
                    data: about
                });
            },
            editAbout: function (about) {
                return $http.put("api/settings/about/" + about.id, about);
            },
            getPrizes: function () {
                return $http.get("/prizes" );
            },
            addPrize:function(prize){
                return $http({
                    url: 'api/settings/prizes',
                    method: 'POST',
                    headers: 'Accept:application/json',
                    data: prize
                });
            },
            editPrize: function (prize) {
                return $http({
                    url: 'api/settings/prizes/' + prize.id,
                    method: 'PUT',
                    headers: 'Accept:application/json',
                    data:prize
                });
            },
            deletePrize: function (id) {
                return $http({
                    url: 'api/settings/prizes/' + id,
                    method: 'DELETE',
                    headers: 'Accept:application/json',
                });
            },
            getRules: function () {
                return $http.get("/constraints" );
            },
            addRule:function(rule){
                return $http({
                    url: 'api/settings/constraints',
                    method: 'POST',
                    headers: 'Accept:application/json',
                    data: rule
                });
            },
            editRule: function (rule) {
                return $http({
                    url: 'api/settings/constraints/' + rule.id,
                    method: 'PUT',
                    headers: 'Accept:application/json',
                    data:rule
                });
            },
            deleteRule: function (id) {
                return $http({
                    url: 'api/settings/constraints/' + id,
                    method: 'DELETE',
                    headers: 'Accept:application/json',
                });
            },
            saveStudentAnswers:function (questionAnswers) {
                return $http({
                    url: 'api/studnet/save',
                    method: 'POST',
                    headers: 'Accept:application/json; charset=utf-8',
                    data: questionAnswers
                });
            },
            validateToken :function (token) {
                return $http({
                    url:'validate',
                    method:'POST',
                    headers: 'Accept:application/json',
                    data:token
                });
            },
            retriveStudentAnswers:function (id) {
                return $http.get("api/studnet/answers/"+id );
            },
            moveToParticipated : function (ids , all) {
                var param ="";
                if(all){
                    param = "?all=true";
                }

                return $http({
                    url: 'api/studnet/partcipate'+param,
                    method: 'PUT',
                    headers: 'Accept:application/json',
                    data:ids
                });
            },
            elimenate: function (ids) {
                return $http({
                    url: 'api/studnet/eleminate',
                    method: 'PUT',
                    headers: 'Accept:application/json',
                    data:ids
                });
            },
            isExist: function (mobile) {
                return $http({
                    url: 'validate/'+mobile,
                    method: 'GET',
                    headers: 'Accept:application/json',
                });
            },
            endTest: function () {
                return $http({
                    url: 'api/questions/endTest',
                    method: 'PUT',
                    headers: 'Accept:application/json',
                });
            },
            moveToCandidate : function (ids , all) {
                var param = "";
                if (all) {
                    param = "?all=true";
                }
                    return $http({
                        url: 'api/studnet/candidate' + param,
                        method: 'PUT',
                        headers: 'Accept:application/json',
                        data: ids
                    });
                },
            getCandidate : function () {
                return $http({
                    url: 'api/studnet/candidate',
                    method: 'GET',
                    headers: 'Accept:application/json',
                });
            },
            getImageName : function () {
                return $http({
                    url: 'settings/image',
                    method: 'GET',
                    headers: 'Accept:application/json',
                });
            },
            deletePrizesImage : function () {
                   return $http({
                    url: 'api/settings/images/' + id,
                    method: 'DELETE',
                    headers: 'Accept:application/json',
                });
            }
        }
    }]);

})();