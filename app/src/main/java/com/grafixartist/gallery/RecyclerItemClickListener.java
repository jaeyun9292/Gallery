package com.grafixartist.gallery;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
  private OnItemClickListener mListener;

  public interface OnItemClickListener {
    public void onItemClick(View view, int position);
  }

  GestureDetector mGestureDetector;

  public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
    mListener = listener;

    //터치가 끝날때 true를 반환하여 onInterceptTouchEvent를 호출
    mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
      @Override public boolean onSingleTapUp(MotionEvent e) {
        return true;
      }
    });
  }

  @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
    // findChildViewUnder는 지정된 점 위의 view를 찾아주는 메서드고,
    // getChildAdapterPosition는 어댑터에서 지정된 view에 해당하는 위치를 반환하는 메서드다.

    View childView = view.findChildViewUnder(e.getX(), e.getY());
    if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
      mListener.onItemClick(childView, view.getChildPosition(childView));
      return true;
    }
    return false;
  }

  @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

  @Override
  public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

  }
}