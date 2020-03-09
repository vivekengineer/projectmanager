import { Component, OnInit } from '@angular/core';
import {TaskService} from '../services/task.service';
import { StaticTaskService } from '../services/static.service';
import { TaskVO } from '../addtask/taskVO';
import { Router } from '@angular/router';
import {ProjectService} from '../services/project.service';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-edittask',
  templateUrl: './edittask.component.html',
  styleUrls: ['./edittask.component.css']
})
export class EdittaskComponent implements OnInit {
  task: TaskVO = new TaskVO();
  projectsList : any = [];
  usersList : any = [];
  parentsList : any = [];
  tempParentsList : any = [];
  projectName:String='';
  userName:String='';
  parentTaskName:String='';
  modalHeading:String = '';
  modalBody:String ='';
  checkBoxSelect: boolean;
  //editTask: any= {};
  constructor(private router: Router,private userService: UserService,private projectService: ProjectService,private taskService: TaskService, private staticTaskService: StaticTaskService) {
    projectService.getProjects().subscribe((data :any) => {
      this.projectsList = data;
      for ( var i = 0; i < this.projectsList.length; i++)
      {
        if(this.projectsList[i].projectId !== null && this.projectsList[i].projectId === this.task.projectId){
          this.projectName=this.projectsList[i].projectName;
        }
      }
     
    });
    userService.getUsers().subscribe((data :any) => {
      this.usersList = data;
      for ( var i = 0; i < this.usersList.length; i++)
      {
        if(this.usersList[i].employeeId !== null && this.usersList[i].employeeId === this.task.employeeId){
          this.userName=this.usersList[i].lastName + ', ' + this.usersList[i].firstName;
        }
      }
    });
    taskService.getParents().subscribe((data :any) => {
      this.parentsList = data;
      this.tempParentsList=data;
      console.log( this.task.employeeId);
      console.log(this.task.parentTaskId);
      console.log(this.parentsList.length);
      for ( var i = 0; i < this.parentsList.length; i++)
      {
        console.log(this.task.parentTaskId);
        if(this.parentsList[i].parentTaskId !== null && this.parentsList[i].parentTaskId === this.task.parentTaskId){
          this.parentTaskName=this.parentsList[i].parentTaskName;
        }
      }
      console.log(this.parentTaskName);
    });
   
  }

  ngOnInit() {
    this.task = this.staticTaskService.editTask;
    this.checkBoxSelect=true;
    console.log(this.task.startDate);
    console.log(this.task.endDate);
    // = new Date();
  //  this.task.endDate = new Date();
    
  }

  updateTask(): void {
    if(this.task.taskName !== '' ){
      this.taskService.updateTask(this.task,this.task.taskId)
      .subscribe( (data: any) => {
        if(data){
          this.modalHeading = 'Note:';
          this.modalBody = 'Task saved Successfully'
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
  }


getParentPopup(){
  this.parentsList = [];
  for ( var i = 0; i < this.tempParentsList.length; i++)
  {
    if(this.tempParentsList[i].parentTaskName.toLowerCase().indexOf(this.parentTaskName.toLowerCase()) > -1){
      this.parentsList.push(this.tempParentsList[i]);
    }
  }
  if(this.parentsList.length ===0){
    this.modalHeading = 'Error!!!';
    this.modalBody = 'No Parent Task found. Please try again.';
    document.getElementById("submitModalhide").click();  
  
  }else{
  document.getElementById("userModalhide").click();
}
}
selectedParent(parent : any){
  this.parentTaskName=parent.parentTaskName;
  this.task.parentTaskId=parent.parentTaskId;
  document.getElementById("modalClose").click();
  

}

}
