import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { HttpService } from '../../services/http.service';

@Component({
  selector: 'app-update-claim',
  templateUrl: './update-claim.component.html',
  styleUrls: ['./update-claim.component.scss']
})

export class UpdateClaimComponent {

  itemForm: FormGroup;
  formModel: any = { description: '', date: '', status: null };

  updatedId: any;
  claimList: any[] = [];

  constructor(
    public router: Router,
    public httpService: HttpService,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
    this.itemForm = this.formBuilder.group({
      description: ['', Validators.required],
      date: ['', Validators.required],
      status: ['', Validators.required]
    });
  }

  getClaims() {
    this.httpService.getAllClaims().subscribe();
  }

  edit(val: any) {
    this.updatedId = val.id;
    this.itemForm.patchValue(val);
  }

  onSubmit() {
    if (this.itemForm.invalid) return;
    this.httpService.updateClaims(this.itemForm.value, this.updatedId).subscribe();
  }
}

   

