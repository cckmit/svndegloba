import { forEach } from '@angular/router/src/utils/collection';
import { Food, Meal, User, Units, Goals, DietDays } from './../model/data-model';
import { Observable } from 'rxjs/Observable';
import { Injectable, Input } from '@angular/core';
// import { FirebaseListObservable, AngularFireDatabase } from 'angularfire2/database';
// import { AngularFireDatabaseModule } from '@angular/fire/database';
import { AuthService } from '../shared/services/auth.service';
import * as firebase from 'firebase/app';
import { AngularFirestore, AngularFirestoreDocument , AngularFirestoreCollection} from '@angular/fire/firestore';

@Injectable()
export class FireService {
  user: Observable<firebase.User>;
  foods: AngularFirestoreCollection<any[]>;
  afAuth: AuthService;
  fireUser: firebase.User;
  database: any;
  calendar: DietDays[];

  constructor(afAuth: AuthService, db: AngularFirestore) {
    this.user = afAuth.afAuth.authState;
    this.foods = db.collection('foods');
    this.afAuth = afAuth;
    this.database = firebase.database();
    this.fireUser = JSON.parse(localStorage.getItem('fireUser'));
  }


  getUser() {
    const database = firebase.database();
    return this.database.ref('/users/' + this.fireUser.uid).once('value');
  }
  getUserData() {
    const database = firebase.database();
    return this.database.ref('/profiles/' + this.fireUser.uid).once('value');
  }
  setUserData(fireUser: firebase.User, user: User) {
    this.database.ref('profiles/' + fireUser.uid).set({
      birthday: user.birthday,
      email: user.email,
      postalCode: user.postalCode,
      country: user.country,
      goals: user.goals,
      units: user.units,
      weight: user.weight,
      height: user.height,
      name: user.name

    });
  }
  createUser(fireUser: firebase.User) {
    return new Promise((resolve, reject) => {
      this.database.ref('users/' + fireUser.uid).set({
        email: fireUser.email,
        profile_picture: fireUser.photoURL
      });
      resolve(0);
    });
  }

  getFoods() {
    return this.database.ref('/foods').once('value');
  }

  getUserDietDays() {
    this.fireUser = JSON.parse(localStorage.getItem('fireUser'));
    return this.database.ref('/dietDays/' + this.fireUser.uid).once('value');
  }
  setUserDietDays(userId, dietDays: DietDays[]) {
    this.database.ref('dietDays/' + userId).set({
      dietDays: dietDays
    });
  }

  getFoodById(id): Promise<Food> {
    return new Promise((resolve, reject) => {
      this.getFoods().then(snapshot => {
        snapshot.forEach(child => {
          const food = child.val();
          if (food.id === id) {
            resolve(food);
          }
        });
      });
    });
  }


}