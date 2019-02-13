import { forEach } from '@angular/router/src/utils/collection';
import { Food, Meal, User, Units, Goals, DietDays } from './../data-model';
import { Observable } from 'rxjs/Observable';
import { Injectable, Input } from '@angular/core';
import { AngularFireList, AngularFireDatabase } from 'angularfire2/database';
import { AngularFireAuth } from 'angularfire2/auth';
import * as firebase from 'firebase/app';
import { AngularFireStorage } from 'angularfire2/storage';

@Injectable()
export class FireService {
  user: Observable<firebase.User>;
  foods: AngularFireList<any[]>;
  afAuth: AngularFireAuth;
  fireUser: firebase.User;
  database: any;
  calendar: DietDays[];

  constructor(afAuth: AngularFireAuth, db: AngularFireDatabase) {
    this.user = afAuth.authState;
    this.foods = db.list('foods');
    console.log('constructor fireserviceeeeee user ' + this.user);
    this.afAuth = afAuth;
    console.log('constructor fireserviceeeeee ' + this.afAuth);
    this.database = firebase.database();
     console.log('constructor fireserviceeeeee ' + this.database + ' LOCALsTORAGE : ' + localStorage);
     this.fireUser = JSON.parse(localStorage.getItem('fireUser'));
     console.log('constructor fireserviceeeeee ' + this.fireUser);
  }
  loginWithGoogle() {
    return this.afAuth.auth.signInWithPopup(new firebase.auth.GoogleAuthProvider());
  }

  logout() {
    return this.afAuth.auth.signOut();
  }

  getUser() {
    const database = firebase.database();
    return this.database.ref('/users/' + this.fireUser.uid).once('value');
  }
  getUserData() {
      console.log('getuserdataaaaaa ' + this.fireUser);
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
