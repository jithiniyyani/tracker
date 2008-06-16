module com.controllers{

  export class mainController{

      private name :string = "This comes from the first controller";
      constructor($scope){
        $scope.vm = this;
      }
      public displayName(){
                return this.name;
      }

  }

}
