import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule }          from '@angular/forms';
import { AddprojectComponent } from './addproject.component';
import {PorjectPipe} from '../addproject/project.pipe';
import { AppRoutingModule } from '../app-routing.module';

describe('AddprojectComponent', () => {
  let component: AddprojectComponent;
  let fixture: ComponentFixture<AddprojectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddprojectComponent,
        PorjectPipe ],
      imports: [
        FormsModule,
        AppRoutingModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddprojectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
