import { Component, OnInit, Pipe, PipeTransform} from '@angular/core';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';
import {UserService} from '../services/user.service';
import { UserVO } from './userVO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.component.html',
  styleUrls: ['./adduser.component.css']
})
export class AdduserComponent implements OnInit {
  user : any = {};
  usersList : any = [];
  tempusersList : any = [];
  modalHeading : string = '';
  modalBody : string = '';
  error:any={isError:false,errorMessage:''};
  searchUser : string = '';

  constructor(private router: Router,private userService: UserService) { 
    this.user = {
      "employeeId":"",
      "firstName":"",
      "lastName":""
    };

    userService.getUsers().subscribe((data :any) => {
      this.usersList = data;
      this.tempusersList=data;
    });

  }

  ngOnInit() {
    this.user = {
      "employeeId":"",
      "firstName":"",
      "lastName":""
    };
  }
  
  addUser(): void {

    console.log(this.user);
    this.user.status="A";
    console.log(this.user.lastName.length);
    if(this.user.lastName != '' && this.user.firstName !='' && this.user.employeeId!=''){
       this.userService.addUser(this.user)
        .subscribe( (data: any) => {
          if(data){
            this.modalHeading = 'Note:';
            this.modalBody = 'User saved Successfully'
            document.getElementById("submitModalhide").click();  
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
    console.log(this.user.status);
  };


  editUser(user : any){
    this.user = {
      "employeeId":user.employeeId,
      "firstName":user.firstName,
      "lastName":user.lastName,
      "status": user.status
    };
    document.getElementById('lastName').focus();
  }  

  sortByFirstName(){
    this.usersList   = [];
    
    this.tempusersList.sort((a, b) => {
      var fNameA = a.firstName.toLowerCase(), fNameB = b.firstName.toLowerCase();
      return fNameA >fNameB?1:fNameA<fNameB?-1:0
    });
    console.log(this.tempusersList);

    this.usersList   = [];
   // this.usersList = this.tempusersList;
   this.usersList = this.tempusersList;
  }

  sortByLastName(){
    this.usersList   = [];
    
    this.tempusersList.sort((a, b) => {
      var lNameA = a.lastName.toLowerCase(), lNameB = b.lastName.toLowerCase();
      return lNameA >lNameB?1:lNameA<lNameB?-1:0
    });
    console.log(this.tempusersList);
    this.usersList = this.tempusersList;
  }

  sortByEmployeeId(){
    this.usersList   = [];
    
    this.tempusersList.sort((a, b) => {
      var eIdA = a.employeeId.toLowerCase(), eIdb = b.employeeId.toLowerCase();
      return eIdA >eIdb?1:eIdA<eIdb?-1:0
    });
    console.log(this.tempusersList);
    this.usersList = this.tempusersList;
  }


  
}
