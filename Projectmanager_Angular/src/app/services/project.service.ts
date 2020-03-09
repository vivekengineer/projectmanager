import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';
import { ProjectVO } from '../addproject/projectVO';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ProjectService {
  //private userUrl = 'http://localhost:8080/user-portal/user';
  private userUrl = 'http://localhost:7070';
  constructor(private http: HttpClient) { }

  public addProject(projectVO) {
    return this.http.post<ProjectVO>(this.userUrl+'/projects', projectVO);
  }

  public getProjects() {
    return this.http.get<ProjectVO>(this.userUrl+'/projects');
  }

  
  
  /* addTask(task, parentTask,startDate,endDate) {
    const uri = 'http://localhost:4000/task/add';
    const obj = {
      task: name,
      parentTask: parentTask,
      startDate:startDate,
      endDate:endDate

    };
    this.http.post(uri, obj)
        .subscribe(res => console.log('Done'));
  } */
}