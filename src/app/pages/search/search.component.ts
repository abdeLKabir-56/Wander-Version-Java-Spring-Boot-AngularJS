import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PostService } from '../../service/post.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'] // corrected styleUrl to styleUrls
})
export class SearchComponent {
  results: any[] = [];
  name: string = "";

  constructor(
    private snackbar: MatSnackBar,
    private postService: PostService,
  ) {}

  searchByName() {
    this.postService.searchByName(this.name).subscribe(
      (res) => {
        this.results = res;
        console.log(res);
      },
      (error) => {
        this.snackbar.open('Something went wrong', 'OK');
      }
    );
  }
}
