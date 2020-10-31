import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { PostActionModalComponent } from '../components/post-action-modal/post-action-modal.component';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(private modalService: NgbModal){

  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          let errorMsg = '';
          if (error.error instanceof ErrorEvent) {
            console.log('this is client side error');
            errorMsg = `Error: ${error.error.message}`;
          }
          else {
            console.log('this is server side error');
            errorMsg = `Error Code: ${error.status},  Message: ${error.message}`;
          }
          const modalRef = this.modalService.open(PostActionModalComponent);
          modalRef.componentInstance.title = 'Error';
          modalRef.componentInstance.content = errorMsg;
          return throwError(errorMsg);
        })
      )
  }
}
