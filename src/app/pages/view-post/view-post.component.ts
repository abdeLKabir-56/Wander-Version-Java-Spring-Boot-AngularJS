import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../../service/post.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommentService } from '../../service/comment.service';

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.scss'] // Use styleUrls for specifying stylesheets
})
export class ViewPostComponent implements OnInit {
  postId = this.activatedRoute.snapshot.params['id']; 
  postData: any;
  comments: any;
  commentForm!: FormGroup;
  constructor(
    private snackbar: MatSnackBar,
    private postService: PostService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private commentService: CommentService
    ) {
    this.getPostById();
  }

  ngOnInit(): void {
    this.postId;
    this.commentForm = this.fb.group({
      postedBy: [null, Validators.required],
      content: [null, [Validators.required]]
    });
  }
  getPostById() {
    this.postService.getPostById(this.postId).subscribe(
      (res) => {
        this.postData=res;
        this.getAllCommentsByPost();
        //console.log(res);
        
      },
      (error) => {
        this.snackbar.open('Something went wrong', 'OK');
      }
    );
  }
  likePost() {
    this.postService.likePost(this.postId).subscribe(
      (res) => {
        this.snackbar.open('Post liked successfully', 'OK');
        this.getPostById();
      },
      (error) => {
        this.snackbar.open('Something went wrong', 'OK');
      }
    );
  }
  createComment(): void {
    const postedBy = this.commentForm.get('postedBy')?.value;
    const content = this.commentForm.get('content')?.value;
    this.commentService.createNewComment(this.postId,postedBy, content).subscribe(
      res =>{
        this.snackbar.open('comment created successfully', 'OK');
        
      },err =>{
        this.snackbar.open('Something went wrong', 'OK');
      }
    );
  }
  getAllCommentsByPost() {
    this.commentService.getAllCommentByPost(this.postId).subscribe(
      (res) => {
        this.comments = res;
        console.log(this.comments);
      },
      (error) => {
        this.snackbar.open('Something went wrong', 'OK');
      }
    );
  }
  
}
