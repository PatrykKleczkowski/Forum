import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material";
import {AngularEditorConfig} from '@kolkov/angular-editor';
import {FormControl, FormGroup} from "@angular/forms";
import {TopicsService} from "@shared/services/topics.service";


@Component({
  selector: 'app-new-topic-dialog',
  templateUrl: './new-topic-dialog.component.html',
  styleUrls: ['./new-topic-dialog.component.scss']
})
export class NewTopicDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private topicService: TopicsService) {
  }

  public newTopicForm: FormGroup;

  editorConfig: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: '25rem',
    minHeight: '5rem',
    placeholder: 'Enter text here...',
    translate: 'no'
  };

  ngOnInit() {
    this.newTopicForm = new FormGroup({
      content: new FormControl(),
      topicTitle: new FormControl()
    })
  }

  saveTopic() {
    const topicTitle: string = this.newTopicForm.value.topicTitle;
    const content: string = this.newTopicForm.value.content;
    const categoryId: number = this.data.categoryId;
    this.topicService.saveNewTopic({topicTitle, content, categoryId}).subscribe();
    window.location.reload();
  }

}
