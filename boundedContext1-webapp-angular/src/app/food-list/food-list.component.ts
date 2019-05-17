import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from './../providers/event.service';
import { ActiveStateService } from './../providers/active-state.service';
import { AngularFireList } from 'angularfire2/database';
import { AuthService } from '../shared/services/auth.service';
import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/finally';
import { Food } from '../model/data-model';
import { MatDialog } from '@angular/material';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/startWith';


@Component({
  selector: 'app-food-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['food-list.component.css']


})
export class FoodListComponent implements OnInit {
  foods: AngularFireList<Food[]>;
  isLoading = false;
  selectedFood: Food;
  private sub: any;
  location = '';
  filteredItems: any;
  filteredOptions: Observable<Food[]>;
  search = '';
  filteredList: Food[];
  myfoods: Food[];
  constructor(private fireService: AuthService,
    private activeStateService: ActiveStateService,
    private route: ActivatedRoute ,
    private router: Router) {

    this.location = router.url;


  }
  ngOnInit() {
   // this.getFoods();

  }

//   getFoods() {
//     this.isLoading = true;
//     this.foods = this.fireService.foods;
//     this.selectedFood = undefined;
//     this.foods.valueChanges().subscribe( snapshot => {
//     // this.myfoods =  snapshot;
//     // this.filteredList = snapshot;
//     });

//   }
  select(food: Food) {
    this.selectedFood = food;
  }

  goToDetail(food: Food) {
    this.router.navigate(['/food-detail', food.id]);
  }


 filter() {
  if (this.search !== '') {
      this.myfoods.filter(el => {
        this.filteredList =  this.myfoods.filter(option => new RegExp(`^${this.search}`, 'gi').test(option.name));

      });
  } else {
      this.filteredList = this.myfoods;
  }
 }
}
