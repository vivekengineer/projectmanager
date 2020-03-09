import {Pipe, PipeTransform,Injectable } from '@angular/core';
import {DatePipe} from '@angular/common';
import {ProjectVO} from './projectVO';

@Pipe({name:'ProjectPipe'})
@Injectable()
export class PorjectPipe implements PipeTransform{
    transform(usersList:ProjectVO[],field:string): ProjectVO[] {
       console.log(field + "field --->"+field)
    
       if (usersList && usersList.length){
        return usersList.filter(item =>{
            if (field === '' || field === undefined || field === null)
            {
                return true;
            }else if (field)
            {
                if(item.projectName.toLowerCase().indexOf(field.toLowerCase()) > -1){
                    return true;
                }
            }else{
                return false;
            }
            return false;
       })
    }
    else{
        return usersList;
    }
    

     
}
}