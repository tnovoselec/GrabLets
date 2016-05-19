package com.grablets.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.jakewharton.rxrelay.PublishRelay;

import javax.inject.Inject;

public class FirebaseAuthClient {

  private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

  private final PublishRelay<FirebaseAuth> firebaseUserPublishRelay = PublishRelay.create();

  private FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuthStateListener();

  @Inject
  public FirebaseAuthClient() {
  }

  public void activateClient() {
    firebaseAuth.addAuthStateListener(authStateListener);
  }

  public void deactivateClient() {
    if (authStateListener != null) {
      firebaseAuth.removeAuthStateListener(authStateListener);
    }
  }

  public void loginUser(String email, String password) {

    if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
      firebaseUserPublishRelay.call(null);
      return;
    }

    firebaseAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener((task) -> {
          if (!task.isSuccessful()) {
            firebaseUserPublishRelay.call(null);
          } else {
            firebaseUserPublishRelay.call(firebaseAuth);
          }
        });
  }

  public void logout() {
    firebaseAuth.signOut();
  }

  public PublishRelay<FirebaseAuth> getFirebaseAuth() {
    return firebaseUserPublishRelay;
  }

  private class FirebaseAuthStateListener implements FirebaseAuth.AuthStateListener {

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//      if (firebaseAuth.getCurrentUser() == null) {
//        Log.e("FirebaseAuthStateL", "user null");
//
//      } else {
//        Log.e("FirebaseAuthStateL", firebaseAuth.getCurrentUser().toString());
//      }
//      firebaseUserPublishRelay.call(firebaseAuth);
    }
  }
}
