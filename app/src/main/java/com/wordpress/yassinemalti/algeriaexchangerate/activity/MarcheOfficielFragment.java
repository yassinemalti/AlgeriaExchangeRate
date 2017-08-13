package com.wordpress.yassinemalti.algeriaexchangerate.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.wordpress.yassinemalti.algeriaexchangerate.R;
import com.wordpress.yassinemalti.algeriaexchangerate.adapter.SimpleRecyclerAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class MarcheOfficielFragment extends Fragment {

    String url = "https://www.devise-dz.com/";
    public RecyclerView recyclerView;
    ProgressDialog progressDialog;
    SimpleRecyclerAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MarcheOfficielFragment() {
        // Required empty public constructor
    }

    public static MarcheOfficielFragment newInstance(String param1, String param2) {
        MarcheOfficielFragment fragment = new MarcheOfficielFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_marche_officiel, container, false);
        NativeExpressAdView adBanner_marcheofficiel = (NativeExpressAdView) rootView.findViewById(R.id.adBanner_marcheofficiel);
        AdRequest request_marcheofficiel = new AdRequest.Builder().build();
        adBanner_marcheofficiel.loadAd(request_marcheofficiel);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.devise_marcheofficiel_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        new LoadPage().execute();
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class LoadPage extends AsyncTask<Void, Void, Void> {
        String desc;
        List<String> list;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("جاري التحديث...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(url).get();
                Element dateDerniereMiseJour = document.select("#secondary p").get(1);
                String dateDerniereMiseJourText = dateDerniereMiseJour.text();
                String tableDesTauxDeChanges = document.select("#secondary table").text();
                String myDesc = dateDerniereMiseJourText + "\n" + tableDesTauxDeChanges;
                List<String> words = new ArrayList<String>();
                words = getWords(myDesc);
                //desc = getWords(myDesc).toString();

                switch (words.get(4)) {
                    case "Janvier" : words.set(4,"جانفي"); break;
                    case "Février" : words.set(4,"فيفري"); break;
                    case "Mars" : words.set(4,"مارس"); break;
                    case "Avril" : words.set(4,"أفريل"); break;
                    case "Mai" : words.set(4,"ماي"); break;
                    case "Juin" : words.set(4,"جوان"); break;
                    case "Juillet" : words.set(4,"جويلية"); break;
                    case "Août" : words.set(4,"أوت"); break;
                    case "Septembre" : words.set(4,"سبتمبر"); break;
                    case "Octobre" : words.set(4,"أكتوبر"); break;
                    case "Novembre" : words.set(4,"نوفمبر"); break;
                    case "Décembre" : words.set(4,"ديسمبر"); break;

                    default : words.set(4,"جانفي");;
                }

                List<Integer> wordsIndex = new ArrayList<Integer>();

                wordsIndex.add(-6); wordsIndex.add(3); wordsIndex.add(4); wordsIndex.add(5); wordsIndex.add(0);
                wordsIndex.add(-7); wordsIndex.add(-8); wordsIndex.add(0);
                wordsIndex.add(-10); wordsIndex.add(10); wordsIndex.add(0);
                wordsIndex.add(-11); wordsIndex.add(16); wordsIndex.add(0);
                wordsIndex.add(-12); wordsIndex.add(22); wordsIndex.add(0);
                wordsIndex.add(-13); wordsIndex.add(29); wordsIndex.add(0);
                wordsIndex.add(-14); wordsIndex.add(36); wordsIndex.add(0);
                wordsIndex.add(-15); wordsIndex.add(43); wordsIndex.add(0);
                wordsIndex.add(-16); wordsIndex.add(50); wordsIndex.add(0);
                wordsIndex.add(-17); wordsIndex.add(57); wordsIndex.add(0);
                wordsIndex.add(-18); wordsIndex.add(64); wordsIndex.add(0);
                wordsIndex.add(-19); wordsIndex.add(71); wordsIndex.add(0);
                wordsIndex.add(-20); wordsIndex.add(78); wordsIndex.add(0);

                desc = "";
                for(int i=0; i<wordsIndex.size(); i++) {
                    switch (wordsIndex.get(i)) {
                        case 0 : desc += "#"; break;
                        case -6 : desc += "آخر تحديث "; break;
                        case -7 : desc += "العملة "; break;
                        case -8 : desc += "السعر "; break;
                        case -9 : desc += "السعر "; break;
                        case -10 : desc += "اليورو "; break;
                        case -11 : desc += "الدولار الأمريكي "; break;
                        case -12 : desc += "الجنيه الإسترليني "; break;
                        case -13 : desc += "الدولار الكندي "; break;
                        case -14 : desc += "الفرنك السويسري "; break;
                        case -15 : desc += "الليرة التركية "; break;
                        case -16 : desc += "اليوان الصيني "; break;
                        case -17 : desc += "الدرهم الإمراتي "; break;
                        case -18 : desc += "الدرهم المغربي "; break;
                        case -19 : desc += "الدينار التونسي "; break;
                        case -20 : desc += "الريال السعودي "; break;
                        default : desc += words.get(wordsIndex.get(i)) + " ";
                    }
                }

                desc = desc.replace(',','.');
                int descLength = desc.length();
                for(int i=0; i<descLength-3; i++) {
                    if(desc.charAt(i) !='.' && desc.charAt(i+1) =='0'
                            && desc.charAt(i+2) =='0' && desc.charAt(i+3) ==' '){
                        desc = new StringBuilder(desc).insert(i+1, ".").toString();
                    }
                }

                list = new ArrayList<String>();

                descLength = desc.length();
                String nouvelleLigne = "";
                for(int i=0; i<descLength-3; i++) {
                    if(desc.charAt(i) == '#' ){
                        list.add(nouvelleLigne);
                        nouvelleLigne = "";
                    } else {
                        nouvelleLigne += desc.charAt(i);
                    }
                }

                adapter = new SimpleRecyclerAdapter(list);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            recyclerView.setAdapter(adapter);
            progressDialog.dismiss();

        }
    }

    public static List<String> getWords(String text) {
        List<String> words = new ArrayList<String>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(text);
        int lastIndex = breakIterator.first();
        while (BreakIterator.DONE != lastIndex) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                words.add(text.substring(firstIndex, lastIndex));
            }
        }

        return words;
    }

}
