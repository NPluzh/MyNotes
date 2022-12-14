package com.example.mynotes.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mynotes.R;
import com.example.mynotes.model.Note;
import com.example.mynotes.model.NoteRepository;

import java.util.List;
import java.util.Optional;


public class AdvancedFragment extends Fragment {

    private static final String ARG_ID_NOTE = "note_id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_advanced, container,
                false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);//!!!!!!!!!!!!!!!!
        Bundle arguments = getArguments();
        if (arguments != null) {
            int idNote = arguments.getInt(ARG_ID_NOTE);//получение индекса заметки которую нужно отобразить в фрагменте детализации заметки//
            NoteRepository notes = NoteRepository.getInstance();
            Note note = notes.getNoteById(idNote);
            setNote(view, note);

            Button buttonBack = view.findViewById(R.id.btnBack);
            buttonBack.setOnClickListener(v -> {
                requireActivity().getSupportFragmentManager().popBackStack();
            });
        }
    }

    public static AdvancedFragment newInstance(int idNote) {
        AdvancedFragment fragment = new AdvancedFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_NOTE, idNote);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_advanced_fragment,menu);
        menu.findItem(R.id.action_about).setVisible(false);
        menu.findItem(R.id.action_exit).setVisible(false);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_back): {
                requireActivity().getSupportFragmentManager().popBackStack();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void setNote(View view, Note note){

        EditText title = view.findViewById(R.id.noteTitle);
        EditText description = view.findViewById(R.id.noteDescription);

        title.setText(note.getTitleNote());
        description.setText(note.getDescriptionNote());

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setTitleNote(title.getText().toString());
                    if(isLandscape()){
                        updateData();
                    }

            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });


        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setDescriptionNote(description.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {    }
        });
    }

    private void updateData() {
        Log.d("MY_TAG","call updateData()");
        HeadingFragment headingFragment = new HeadingFragment();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,headingFragment)
                .commit();
    }


    private HeadingFragment getCurrentHeadingFragment() {
        HeadingFragment headingFragment = null;
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        List<Fragment> currentFragments = fragmentManager.getFragments();
        for (Fragment fragment:currentFragments) {
            if(fragment instanceof HeadingFragment){
                headingFragment = (HeadingFragment) fragment;
            }
        }
        return headingFragment;
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }


}
