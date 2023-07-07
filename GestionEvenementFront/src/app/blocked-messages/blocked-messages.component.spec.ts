import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlockedMessagesComponent } from './blocked-messages.component';

describe('BlockedMessagesComponent', () => {
  let component: BlockedMessagesComponent;
  let fixture: ComponentFixture<BlockedMessagesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BlockedMessagesComponent]
    });
    fixture = TestBed.createComponent(BlockedMessagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
