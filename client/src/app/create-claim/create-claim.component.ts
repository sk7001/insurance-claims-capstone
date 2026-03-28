import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpService } from '../../services/http.service';
import { AuthService } from '../../services/auth.service';


@Component({
  selector: 'app-create-claim',
  templateUrl: './create-claim.component.html',
  styleUrls: ['./create-claim.component.scss']
})

export class CreateClaimComponent {

  itemForm: FormGroup;
  formModel: any = { description: '', date: '', status: '' };

  showError = false;
  errorMessage: any;
  showMessage: any;
  responseMessage: any;

  claimList: any[] = [];

  constructor(
    public router: Router,
    public httpService: HttpService,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
    this.itemForm = this.formBuilder.group({
      description: [this.formModel.description, Validators.required],
      date: [this.formModel.date, Validators.required],
      status: [this.formModel.status, Validators.required]
    });
  }

  getClaims() {
    const id = localStorage.getItem('userId');
    this.httpService.getClaimsByPolicyholder(id).subscribe();
  }

  onSubmit() {
    if (this.itemForm.invalid) {
      this.showError = true;
      return;
    }
    const id = localStorage.getItem('userId');
    this.httpService.createClaims(this.itemForm.value, id).subscribe();
  }
}


