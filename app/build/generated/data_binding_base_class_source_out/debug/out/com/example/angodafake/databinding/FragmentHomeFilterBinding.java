// Generated by view binder compiler. Do not edit!
package com.example.angodafake.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.angodafake.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeFilterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView HomeTittle;

  @NonNull
  public final Button backToMain;

  @NonNull
  public final RecyclerView contactsRV;

  @NonNull
  public final TextView filter;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final TextView nameHotelSearch;

  @NonNull
  public final TextView price;

  @NonNull
  public final TextView sort;

  private FragmentHomeFilterBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView HomeTittle, @NonNull Button backToMain, @NonNull RecyclerView contactsRV,
      @NonNull TextView filter, @NonNull ImageView imageView3, @NonNull TextView nameHotelSearch,
      @NonNull TextView price, @NonNull TextView sort) {
    this.rootView = rootView;
    this.HomeTittle = HomeTittle;
    this.backToMain = backToMain;
    this.contactsRV = contactsRV;
    this.filter = filter;
    this.imageView3 = imageView3;
    this.nameHotelSearch = nameHotelSearch;
    this.price = price;
    this.sort = sort;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeFilterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home_filter, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeFilterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.HomeTittle;
      TextView HomeTittle = ViewBindings.findChildViewById(rootView, id);
      if (HomeTittle == null) {
        break missingId;
      }

      id = R.id.backToMain;
      Button backToMain = ViewBindings.findChildViewById(rootView, id);
      if (backToMain == null) {
        break missingId;
      }

      id = R.id.contactsRV;
      RecyclerView contactsRV = ViewBindings.findChildViewById(rootView, id);
      if (contactsRV == null) {
        break missingId;
      }

      id = R.id.filter;
      TextView filter = ViewBindings.findChildViewById(rootView, id);
      if (filter == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.nameHotelSearch;
      TextView nameHotelSearch = ViewBindings.findChildViewById(rootView, id);
      if (nameHotelSearch == null) {
        break missingId;
      }

      id = R.id.price;
      TextView price = ViewBindings.findChildViewById(rootView, id);
      if (price == null) {
        break missingId;
      }

      id = R.id.sort;
      TextView sort = ViewBindings.findChildViewById(rootView, id);
      if (sort == null) {
        break missingId;
      }

      return new FragmentHomeFilterBinding((ConstraintLayout) rootView, HomeTittle, backToMain,
          contactsRV, filter, imageView3, nameHotelSearch, price, sort);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}