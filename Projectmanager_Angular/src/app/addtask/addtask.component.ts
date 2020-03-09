import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
import {TaskService} from '../services/task.service';
import { StaticTaskService } from '../services/static.service';
import { TaskVO } from '../addtask/taskVO';
import { Router } from '@angular/router';
import {ProjectService} from '../services/project.service';
import {UserService} from '../services/user.service';

 
@Component({
  selector: 'app-addtask',
  templateUrl: './addtask.component.html',
  styleUrls: ['./addtask.component.css']
})
export class AddtaskComponent implements OnInit {
  task: TaskVO = new TaskVO();
  projectsList : any = [];
  tempProjectsList : any = [];
  usersList : any = [];
  tempusersList : any = [];
  parentsList : any = [];
  tempParentsList : any = [];
  projectName:String='';
  userName:String='';
  parentTaskName:String='';
  searchProjects:String='';
  modalHeading:String = '';
  modalBody:String ='';
  checkBoxSelect: boolean;

   
  constructor(private router: Router,private userService: UserService,private projectService: ProjectService,private taskService: TaskService, private staticTaskService: StaticTaskService) {
    
    projectService.getProjects().subscribe((data :any) => {
       this.projectsList = data;
       this.tempProjectsList=data;
     });
    
     userService.getUsers().subscribe((data :any) => {
        this.usersList = data;
        this.tempusersList=data;
     });
    
    taskService.getParents().subscribe((data :any) => {
        this.parentsList = data;
        this.tempParentsList=data;
      });
   }

   
   error:any={isError:false,errorMessage:''};
   
   compareTwoDates(){
     this.error={isError:false,errorMessage:''};
      if(new Date(this.task.endDate)<new Date(this.task.startDate)){
         this.error={isError:true,errorMessage:'End Date can not before start date'};
      }
   }



   getProjectPopup(){
    this.projectsList = [];
    console.log(this.tempProjectsList);
    for ( var i = 0; i < this.tempProjectsList.length; i++)
    {
      if(this.tempProjectsList[i].projectName.toLowerCase().indexOf(this.searchProjects.toLowerCase()) > -1){
        this.projectsList.push(this.tempProjectsList[i]);
      }
    }
    console.log(this.projectsList);
    if(this.projectsList.length ===0){
      this.modalHeading = 'Error!!!';
      this.modalBody = 'No Project found. Please try again.';
      document.getElementById("submitModalhide").click();  
    
    }else{
    document.getElementById("projectModalhide").click();
    }
 }
 
 selectedProject(item : any){
  console.log(item);
  var projectId=item.projectId;
  console.log(projectId);
  this.projectName=item.projectName;
  this.task.projectId=item.projectId;
  document.getElementById("projectModalClose").click();
}

getParentPopup(){
  this.parentsList = [];
  if(this.task.projectId===null || this.task.projectId===undefined){
    this.modalHeading = 'Error!!!';
    this.modalBody = 'No Project found. Please Select a Project.';
    document.getElementById("submitModalhide").click();  
  }
  else{
      for ( var i = 0; i < this.tempParentsList.length; i++)
      {
        console.log(this.task.projectId);
        console.log(this.tempParentsList[i]);
        console.log(this.parentTaskName.toLowerCase());
        if(this.tempParentsList[i].parentTaskName.toLowerCase().indexOf(this.parentTaskName.toLowerCase()) > -1){
          console.log("check");
          console.log(this.tempParentsList[i].projectId);
          if(this.task.projectId===this.tempParentsList[i].projectId){
            this.parentsList.push(this.tempParentsList[i]);
          }
        }
      }
      if(this.parentsList.length ===0){
        this.modalHeading = 'Error!!!';
        this.modalBody = 'No Parent Task found. Please try again.';
        document.getElementById("submitModalhide").click();  
      
      }else{
      document.getElementById("parentModalhide").click();
    }
  }
}

selectedParent(parent : any){
  this.parentTaskName=parent.parentTaskName;
  this.task.parentTaskId=parent.parentTaskId;
  this.task.parentTaskName=parent.parentTaskName;
  document.getElementById("parentModalClose").click();
}

getUserPopup(){
  this.usersList = [];
  for ( var i = 0; i < this.tempusersList.length; i++)
  {
    if(this.tempusersList[i].employeeId.toLowerCase().indexOf(this.userName.toLowerCase()) > -1){
      this.usersList.push(this.tempusersList[i]);
    }else if(this.tempusersList[i].firstName.toLowerCase().indexOf(this.userName.toLowerCase()) > -1){
      this.usersList.push(this.tempusersList[i]);
    }else if(this.tempusersList[i].lastName.toLowerCase().indexOf(this.userName.toLowerCase()) > -1){
      this.usersList.push(this.tempusersList[i]);
    }
  }
  if(this.usersList.length ===0){
    this.modalHeading = 'Error!!!';
    this.modalBody = 'No user found. Please try again.';
    document.getElementById("submitModalhide").click();  
  
  }else{
  document.getElementById("userModalhide").click();
}
}

selectedUser(user : any){
  this.userName = user.firstName+' '+user.lastName;
  this.task.employeeId=user.employeeId;
  document.getElementById("userModalClose").click();
  

}


   addTask(): void {

    console.log(this.task.projectId);
    this.task.status="a";
    console.log(this.task.startDate);
    if(this.task.taskName !== '' && this.task.projectId!== undefined 
        && this.task.startDate!== undefined && this.task.endDate!== undefined && this.task.employeeId!==undefined){
     this.taskService.addTask(this.task)
        .subscribe( data => {
          if(data){
            this.modalHeading = 'Note:';
            this.modalBody = 'Task saved Successfully'
            document.getElementById("submitModalhide").click();  
            document.getElementById("reset").click();  
            this.ngOnInit();
          }
          else{
            this.modalHeading = 'Error!!!';
            this.modalBody = 'error occured on Adding Use. Please try again.';
            document.getElementById("submitModalhide").click();  
            this.ngOnInit();
          }
        });
    }else{
      console.log('submitModal');
      this.modalHeading = 'Alert';
      this.modalBody = 'Please fill all required values';
      document.getElementById("submitModalhide").click(); 
    }

  };

  /*addTask(task, parentTask,startDate, endDate) {
    this.taskervice.addTask(task,parentTask,startDate,endDate);
  }*/
  ngOnInit() {
    this.task.taskName ='';
    this.task.priority=15;
    this.checkBoxSelect=true;
     
  }
  formatLabel(value: number | null) {
    if (!value) {
      return 0;
    }

    if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }

    return value;
  }
}
