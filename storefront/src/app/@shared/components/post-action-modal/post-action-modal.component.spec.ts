import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostActionModalComponent } from './post-action-modal.component';

describe('PostActionModalComponent', () => {
  let component: PostActionModalComponent;
  let fixture: ComponentFixture<PostActionModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostActionModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostActionModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
