import { Component, OnInit } from '@angular/core';
import {Topic} from "@shared/models/Topic";
import {TopicsService} from "@shared/services/topics.service";

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {



  constructor() { }

  ngOnInit() {
  }


}
