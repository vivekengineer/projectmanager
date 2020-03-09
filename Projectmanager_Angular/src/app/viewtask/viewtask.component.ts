import { Component, OnInit,Pipe, PipeTransform } from '@angular/core';
import {TaskService} from '../services/task.service';
import { TaskVO } from '../addtask/taskVO';
import { Router } from '@angular/router';
import { StaticTaskService } from '../services/static.service';
import {ProjectService} from '../services/project.service';

@Component({
  selector: 'app-viewtask',
  templateUrl: './viewtask.component.html',
  styleUrls: ['./viewtask.component.css']
})
 
export class ViewtaskComponent implements OnInit{
  project : any = [];
  projectsList : any = [];
  alltaskList : any = [];
  tempTaskList : any = [];
  tasksList : any = [];
  tempProjectsList : any = [];
  task: TaskVO = new TaskVO();
  taskList: any[] = [];
  searchProjects:String='';
  modalHeading : string = 'Select the manager';
  modalBody : string = '';

  constructor(private router: Router,private projectService: ProjectService,private taskService: TaskService, private staticTaskService: StaticTaskService) {
    this.projectService.getProjects().subscribe((data :any) => {
      this.tempProjectsList = data;
    });
    this.taskService.viewTask().subscribe((data :any) => {
      this.alltaskList = data;
      this.tempTaskList=data;
    });

  }
   

  /**viewTask(): void {
   this.taskervice.viewTask()
       .subscribe( data => {
         this.taskList = data;
       },
       error => {
       // alert("ERROR");
      });


 };*/


editTask(task : any){
  this.staticTaskService.editTask = task;
  this.router.navigate(['/edittask']);
  
}

endTask(task : any){
  task.status='I';
  this.taskService.endTask(task,task.taskId)
  .subscribe( data => {
    if(data){
      this.modalHeading = 'Note:';
      this.modalBody = 'Task saved Successfully'
      document.getElementById("submitModalhide").click();  
      this.ngOnInit();
    }
    else{
      this.modalHeading = 'Error!!!';
      this.modalBody = 'error occured on Saving Task. Please try again.';
      document.getElementById("submitModalhide").click();  
      this.ngOnInit();
    }
  },error => {
     alert("ERROR");
   });
  
}


 ngOnInit() {
  //this.viewTask();
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
  if(projectId !== undefined && projectId !== null && projectId !== ''){
    console.log("alltaskList");
    console.log(this.alltaskList);
    this.taskList = [];
    for ( var i = 0; i < this.alltaskList.length; i++)
    {
      if(this.alltaskList[i].projectId !== null && this.alltaskList[i].projectId === projectId){
        this.taskList.push(this.alltaskList[i]);
      }
    }
  }else{
    this.taskList =[] ;
  }

  document.getElementById("modalClose").click();
}


sortByStartDate(){
 // this.taskList = [];
  this.taskList.sort((a, b) => {
    return new Date(a.startDate).getTime() - new Date(b.startDate).getTime();
  });
 // this.taskList =  this.tempTaskList;
  console.log(this.taskList);
}

sortByEndDate(){
 // this.taskList = [];
  this.taskList.sort((a, b) => {
    return new Date(a.endDate).getTime() - new Date(b.endDate).getTime();
  });
  //this.taskList =  this.tempTaskList;
  console.log(this.taskList);
}

sortByPriority(){
  //this.taskList = [];
  this.taskList.sort((a, b) => {
    return parseInt(a.priority) - parseInt(b.priority);
  });
  //this.taskList =  this.tempTaskList;
  console.log(this.taskList);
}

sortByStatus(){
  //this.taskList = [];
  this.taskList.sort((a, b) => {
    var titleA = a.status.toLowerCase(), titleB = b.status.toLowerCase();
    if (titleA < titleB) return -1; 
    if (titleA > titleB) return 1;
    return 0;
  });
  //this.taskList =  this.tempTaskList;
}

}
