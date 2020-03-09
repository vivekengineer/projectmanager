import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';
import {ProjectService} from '../services/project.service';
import {UserService} from '../services/user.service';
import { ProjectVO } from './projectVO';
import { Router } from '@angular/router';



@Component({
  selector: 'app-addproject',
  templateUrl: './addproject.component.html',
  styleUrls: ['./addproject.component.css']
})
export class AddprojectComponent implements OnInit {
  project : any = {};
  user : any = {};
  projectsList : any = [];
  tempProjectList : any = [];
  usersList : any = [];
  tempusersList : any = [];

  modalHeading : string = 'Select the manager';
  modalBody : string = '';
  checkBoxSelect: boolean;

  constructor(private router: Router,private projectService: ProjectService,private userService: UserService) { 
    
    this.project = {
      "projectId":"",
      "projectName":"",
      "priority":"15",
      "managerName":"",
      "managerId":""
    };

    userService.getUsers().subscribe((data :any) => {
      console.log("cd");
      this.usersList = data;
      this.tempusersList=data;
    });
    projectService.getProjects().subscribe((data :any) => {
      console.log("cdd")+data;
      this.projectsList = data;
      this.tempProjectList = data;
    });

  }


  ngOnInit() {
    this.checkBoxSelect=true;
    this.project = {
      "projectId":"",
      "projectName":"",
      "priority":"15",
      "managerName":"",
      "managerId":"",
      "employeeId":""
    };
  }

  addProject(): void {
    console.log(this.project);
        this.project.status='In-Progress';
       
        if(this.project.projectName != '' && this.project.managerName !='' && this.project.employeeId!=''){
          this.projectService.addProject(this.project)
            .subscribe( (data: any) => {
              if(data){
                this.modalHeading = 'Note:';
                this.modalBody = 'Project saved Successfully'
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
        this.ngOnInit();
        console.log(this.project.status);
      };

  update(project : any){
    
    var manager : any = {};
    for ( var i = 0; i < this.usersList.length; i++)
    {
      if(this.usersList[i].employeeId === project.employeeId){
        manager = this.usersList[i];
      }
    }
    var managerName = '';
    var managerId = '';
    if(manager.status === 'A'){
      managerName = manager.lastName + ', ' + manager.firstName;
      managerId = manager.employeeId; 
    }
    console.log(managerName);
    this.project = {
      "projectId":project.projectId,
      "projectName":project.projectName,
      "startDate":project.startDate,
      "endDate": project.endDate,
      "priority":project.priority,
      "status":project.status,
      "employeeId": managerId,
      "managerName": managerName
    };
    console.log(this.project);
    document.getElementById('projectName').focus();
  }
  getUserPopup(){
    this.project.managerId='';
    this.usersList = [];
    for ( var i = 0; i < this.tempusersList.length; i++)
    {
      if(this.tempusersList[i].employeeId.toLowerCase().indexOf(this.project.managerName.toLowerCase()) > -1){
        this.usersList.push(this.tempusersList[i]);
      }else if(this.tempusersList[i].firstName.toLowerCase().indexOf(this.project.managerName.toLowerCase()) > -1){
        this.usersList.push(this.tempusersList[i]);
      }else if(this.tempusersList[i].lastName.toLowerCase().indexOf(this.project.managerName.toLowerCase()) > -1){
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
    this.project.managerName = user.firstName+' '+user.lastName;
    this.project.employeeId=user.employeeId;
    console.log(this.project.employeeId);
    document.getElementById("modalClose").click();
    
  
  }

  suspend(project: any){
    project.status = 'Completed';
    this.project = {
      "projectId":project.projectId,
      "projectName":project.projectName,
      "startDate":project.startDate,
      "endDate": project.endDate,
      "priority":project.priority,
      "status":project.status,
      "employeeId": project.employeeId
    };
    this.projectService.addProject(this.project)
    .subscribe( (data: any) => {
      if(data){
        this.modalHeading = 'Note:';
        this.modalBody = 'User saved Successfully'
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
  }


  sortByStartDate(){
    this.projectsList = [];
    this.projectsList = this.tempProjectList;
    this.projectsList.sort((a, b) => {
      return new Date(a.startDate).getTime() - new Date(b.startDate).getTime();
    });

    console.log(this.projectsList);
  }

  sortByEndDate(){
    this.projectsList = [];
    this.projectsList = this.tempProjectList;
    this.projectsList.sort((a, b) => {
      return new Date(a.endDate).getTime() - new Date(b.endDate).getTime();
    });
  }

  sortByPriority(){
    this.projectsList = [];
    this.projectsList = this.tempProjectList;
    this.projectsList.sort((a, b) => {
      return parseInt(a.priority) - parseInt(b.priority);
    });
  }

  sortByStatus(){
    this.projectsList = [];
    this.projectsList = this.tempProjectList;
    this.projectsList.sort((a, b) => {
      var titleA = a.status.toLowerCase(), titleB = b.status.toLowerCase();
      if (titleA < titleB) return -1; 
      if (titleA > titleB) return 1;
      return 0;
    });
  }

}
