import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material";
import {AngularEditorConfig} from '@kolkov/angular-editor';
import {FormControl, FormGroup} from "@angular/forms";


@Component({
  selector: 'app-new-topic-dialog',
  templateUrl: './new-topic-dialog.component.html',
  styleUrls: ['./new-topic-dialog.component.scss']
})
export class NewTopicDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
  }

  public newTopicForm: FormGroup;


  ngOnInit() {
    console.log(this.data);
    this.newTopicForm = new FormGroup({
      htmlContent: new FormControl(),
      title: new FormControl()
    })
  }

  editorConfig: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: '25rem',
    minHeight: '5rem',
    placeholder: 'Enter text here...',
    translate: 'no'
  };

  //TODO
  saveTopic() {

  }
}
