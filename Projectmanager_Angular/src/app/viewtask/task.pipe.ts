import {Pipe, PipeTransform,Injectable } from '@angular/core';
import {DatePipe} from '@angular/common';
import {TaskVO} from '../addtask/taskVO';

@Pipe({name:'filter'})
@Injectable()
export class TaskPipe implements PipeTransform{
    transform(taskList:TaskVO[],field:string,value:string): TaskVO[] {
       console.log(field + "value --->"+value)
    if (!taskList) {
        return [];
    }
    if (!field || !value) {
        return taskList;
    }
  
   
     
}
}