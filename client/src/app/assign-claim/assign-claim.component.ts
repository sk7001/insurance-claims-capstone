import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { HttpService } from '../../services/http.service';


@Component({
  selector: 'app-assign-claim',
  templateUrl: './assign-claim.component.html',
  styleUrls: ['./assign-claim.component.scss']
})
export class AssignClaimComponent {

  itemForm: FormGroup;
  formModel: any = { claimId: null, underwriterId: null };

  showError = false;
  errorMessage: any;
  showMessage: any;
  responseMessage: any;

  claimList: any[] = [];
  underwriterList: any[] = [];

  constructor(
    public router: Router,
    public httpService: HttpService,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
    this.itemForm = this.formBuilder.group({
      claimId: [this.formModel.claimId, Validators.required],
      underwriterId: [this.formModel.underwriterId, Validators.required]
    });
  }

  onSubmit() {
    if (this.itemForm.invalid) {
      this.showError = true;
      return;
    }
    this.httpService.AssignClaim(this.itemForm.value).subscribe();
  }

  getClaims() {
    this.httpService.getAllClaims().subscribe();
  }

  getUnderwriter() {
    this.httpService.GetAllUnderwriter().subscribe();
  }
}
  

