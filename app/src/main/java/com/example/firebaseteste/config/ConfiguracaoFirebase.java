package com.example.firebaseteste.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracaoFirebase {

    private static DatabaseReference dtReference;
    private static FirebaseAuth auth;
    private static StorageReference storage;

    public static DatabaseReference getFirebaseDatabase(){
        if(dtReference == null){
            dtReference = FirebaseDatabase.getInstance().getReference();
        }
        return dtReference;
    }

    public static FirebaseAuth getFirebaseAutenticacao(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static StorageReference getFirebaseStorage(){
        if(storage == null){
            storage = FirebaseStorage.getInstance().getReference();
        }
        return storage;
    }


}
