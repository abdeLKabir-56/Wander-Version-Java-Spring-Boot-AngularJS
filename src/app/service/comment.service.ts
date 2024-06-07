import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
const BASIC_URL = "http://localhost:8080/";
@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  createNewComment(postId: number, postedBy: string, content: string): Observable<any> {
    const params = new HttpParams()
      .set('postId', postId.toString())
      .set('postedBy', postedBy)
      .set('content', content);

    return this.http.post<any>(BASIC_URL + 'api/comments/create', content, { params });
  }
  getAllCommentByPost(postId : number): Observable<any> {
    return this.http.get(BASIC_URL + `api/comments/${postId}`);
  }
}
