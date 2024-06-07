import { Component } from '@angular/core';
import { PostService } from '../../service/post.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-view-all',
  templateUrl: './view-all.component.html',
  styleUrls: ['./view-all.component.scss']
})
export class ViewAllComponent {
 
  posts:any;
  constructor(
    private snackbar: MatSnackBar,
    private postService: PostService
  ) {
    this.getAllPosts();
  }

  getAllPosts() {
    this.postService.getAllPosts().subscribe(
      (res) => {
        //console.log(res);
        this.posts = res;
      },
      (error) => {
        this.snackbar.open('Something went wrong', 'OK');
      }
    );
  }
}