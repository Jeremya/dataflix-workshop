(function() {

    var TopVideosController = function ($scope, $rootScope, $routeParams, $log, $location, $window, videoFactory, commonFactory) {


        var genre = $routeParams.genre;
        $log.log(genre);
        $scope.genre = genre;

        var mindate = $routeParams.mindate;
                $log.log(mindate);
                $scope.mindate = mindate;

        var maxdate = $routeParams.maxdate;
        $log.log(maxdate);
        $scope.maxdate = maxdate;


        //Retrieves video info
        $scope.getVideosByGenre = function(genre, mindate, maxdate) {
            $log.log("Retrieving videos for " + genre);
            videoFactory.getVideosByGenre(genre, mindate, maxdate)
                .success(function(videos) {
                    $log.log("Videos of genre "+ genre +" retrieved successfully");
                    $scope.videos = videos;
                })
                .error(commonFactory.evaluateError);
        }
    };

    TopVideosController.$inject = ['$scope', '$rootScope', '$routeParams', '$log', '$location', '$window', 'videoFactory', 'commonFactory'];

    angular.module('killrVideoApp').controller('TopVideosController', TopVideosController);

}());