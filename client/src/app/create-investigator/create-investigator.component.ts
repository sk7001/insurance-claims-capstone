import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { HttpService } from '../../services/http.service';

@Component({
  selector: 'app-create-investigator',
  templateUrl: './create-investigator.component.html',
  styleUrls: ['./create-investigator.component.scss']
})

export class CreateInvestigatorComponent {

  itemForm: FormGroup;
  formModel: any = { report: '', status: '' };

  showError = false;
  errorMessage: any;
  showMessage: any;
  responseMessage: any;

  investigationList: any[] = [];
  updateId: any;

  constructor(
    public router: Router,
    public httpService: HttpService,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
    this.itemForm = this.formBuilder.group({
      report: [this.formModel.report, Validators.required],
      status: [this.formModel.status, Validators.required]
    });
  }

  getInvestigation() {
    this.httpService.getInvestigations().subscribe();
  }

  edit(val: any) {
    this.updateId = val.id;
    this.itemForm.patchValue(val);
  }

  onSubmit() {
    if (this.itemForm.invalid) return;

    if (this.updateId) {
      this.httpService.updateInvestigation(this.itemForm.value, this.updateId).subscribe();
    } else {
      this.httpService.createInvestigation(this.itemForm.value).subscribe();
    }
  }
}   

