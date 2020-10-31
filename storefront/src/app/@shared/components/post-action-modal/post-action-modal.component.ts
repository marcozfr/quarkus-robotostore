import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-post-action-modal',
  templateUrl: './post-action-modal.component.html',
  styleUrls: ['./post-action-modal.component.scss']
})
export class PostActionModalComponent implements OnInit {

  @Input() title;
  @Input() content;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

}
