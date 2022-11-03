package com.triton.johnson_tap_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.johnson_tap_app.BD_model;
import com.triton.johnson_tap_app.R;

import java.util.List;

public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {

	private List<BD_model> stList;

	public CardViewDataAdapter(List<BD_model> students) {
		this.stList = students;

	}

	// Create new views
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		// create a new view
		View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.bd_card, null);

		// create ViewHolder

		ViewHolder viewHolder = new ViewHolder(itemLayoutView);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {

		final int pos = position;

		viewHolder.tvName.setText(stList.get(position).getName());

		viewHolder.chkSelected.setChecked(stList.get(position).isSelected());

		viewHolder.chkSelected.setTag(stList.get(position));

		
		viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CheckBox cb = (CheckBox) v;
				BD_model contact = (BD_model) cb.getTag();

				contact.setSelected(cb.isChecked());
				stList.get(pos).setSelected(cb.isChecked());


			}
		});

	}

	// Return the size arraylist
	@Override
	public int getItemCount() {
		return stList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView tvName;

		public CheckBox chkSelected;

		public BD_model singlestudent;

		public ViewHolder(View itemLayoutView) {
			super(itemLayoutView);

			tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);
			chkSelected = (CheckBox) itemLayoutView
					.findViewById(R.id.chkSelected);

		}

	}

	// method to access in activity after updating selection
	public List<BD_model> getStudentist() {
		return stList;
	}

}
