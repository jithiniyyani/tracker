module com.controllers{

    export class carController{

          private name :string = "Default car";

          constructor($scope){
            $scope.vm = this;
          }

          public getName() :string{
            return this.name;

          }

    }

}
