import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookTradeComponent } from './book-trade.component';

describe('BookTradeComponent', () => {
  let component: BookTradeComponent;
  let fixture: ComponentFixture<BookTradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookTradeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookTradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
