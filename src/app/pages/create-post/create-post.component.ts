import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { PostService } from '../../service/post.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
  postForm!: FormGroup;
  tags: string[] = [];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private snackbar: MatSnackBar,
    private postService: PostService
  ) {}

  ngOnInit(): void {
    this.postForm = this.fb.group({
      name: [null, Validators.required],
      content: [null, [Validators.required, Validators.maxLength(5000)]],
      image: [null, Validators.required],
      postedBy: [null, Validators.required]
    });
  }

  add(event: any): void {
    const value = (event.value || '').trim();
    if (value) {
      this.tags.push(value);
    }
    event.chipInput!.clear();
  }

  remove(tag: string): void {
    const index = this.tags.indexOf(tag);
    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }

  createPost(): void {
    const data = { ...this.postForm.value, tags: this.tags };
    this.postService.createNewPost(data).subscribe(
      () => {
        this.snackbar.open('Post created successfully', 'OK');
        this.router.navigateByUrl('/');
      },
      () => {
        this.snackbar.open('Something went wrong', 'OK');
      }
    );
  }
}
