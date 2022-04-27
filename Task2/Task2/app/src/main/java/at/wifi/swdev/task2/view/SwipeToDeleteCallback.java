package at.wifi.swdev.task2.view;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import at.wifi.swdev.task2.R;
import at.wifi.swdev.task2.db.Book;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private BookViewModel viewModel;
    private BookListAdapter adapter;
    private Drawable icon;
    private Book recentlyDeletedBook;
    private ColorDrawable background;

    public SwipeToDeleteCallback(BookListAdapter adapter, BookViewModel viewModel) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);

        this.adapter = adapter;
        this.viewModel = viewModel;
        icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.ic_delete);
        background = new ColorDrawable(adapter.getContext().getColor(R.color.red));
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;
        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + iconMargin;
        int iconBottom = iconTop + icon.getIntrinsicHeight();

        if(dX > 0) {
            int slideIn = 0;
            if((int) dX < iconMargin + icon.getIntrinsicWidth() + iconMargin) {
                slideIn = (int) dX - icon.getIntrinsicWidth() - 2 * iconMargin;
            }

            background.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + (int) dX, itemView.getBottom());
            icon.setBounds(itemView.getLeft() + iconMargin + slideIn, iconTop, itemView.getLeft() + iconMargin + icon.getIntrinsicHeight() + slideIn, iconBottom);
        } else if(dX < 0) {
            int slideIn = 0;
            if((int) dX > - iconMargin - icon.getIntrinsicWidth() - iconMargin) {
                slideIn = (int) dX + icon.getIntrinsicWidth() + 2 * iconMargin;
            }

            background.setBounds(itemView.getRight(), itemView.getTop(), itemView.getRight() + (int) dX, itemView.getBottom());
            icon.setBounds(itemView.getRight() - iconMargin - icon.getIntrinsicHeight() + slideIn, iconTop, itemView.getRight() - iconMargin + slideIn, iconBottom);
        } else {
            background.setBounds(0, 0, 0, 0);
            icon.setBounds(0, 0, 0, 0);
        }
        background.draw(canvas);
        icon.draw(canvas);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        recentlyDeletedBook = adapter.getBookAt(position);
        viewModel.delete(recentlyDeletedBook);
        showUndoSnackbar(viewHolder.itemView);
    }

    private void showUndoSnackbar(View view) {
        Snackbar.make(view, "Buch wurde gelöscht", Snackbar.LENGTH_LONG)
                .setAction("Wiederherstellen", v -> viewModel.insert(recentlyDeletedBook)).show();
    }

}
