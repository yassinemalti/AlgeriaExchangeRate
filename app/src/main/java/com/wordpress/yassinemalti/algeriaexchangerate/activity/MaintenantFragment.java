package com.wordpress.yassinemalti.algeriaexchangerate.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.wordpress.yassinemalti.algeriaexchangerate.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class MaintenantFragment extends Fragment {

    private static final String TAG = "MaintenantFragment";
    public TextView txtDesc;
    String url = "https://www.devise-dz.com/";
    ProgressDialog progressDialog;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MaintenantFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MaintenantFragment newInstance(String param1, String param2) {
        MaintenantFragment fragment = new MaintenantFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_maintenant, container, false);
        NativeExpressAdView adBanner_maintenant = (NativeExpressAdView) rootView.findViewById(R.id.adBanner_maintenant);
        AdRequest request_maintenant = new AdRequest.Builder().build();
        adBanner_maintenant.loadAd(request_maintenant);
        txtDesc = (TextView) rootView.findViewById(R.id.desctxt00);

        new Description().execute();
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
        void onFragmentInteraction(Uri uri);
    }

    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;

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

                wordsIndex.add(3); wordsIndex.add(4); wordsIndex.add(5); wordsIndex.add(0);
                wordsIndex.add(-7); wordsIndex.add(-8); wordsIndex.add(-9);wordsIndex.add(0);
                wordsIndex.add(-10); wordsIndex.add(10); wordsIndex.add(12);wordsIndex.add(0);
                wordsIndex.add(-11); wordsIndex.add(16); wordsIndex.add(18);wordsIndex.add(0);
                wordsIndex.add(-12); wordsIndex.add(22); wordsIndex.add(24);wordsIndex.add(0);
                wordsIndex.add(-13); wordsIndex.add(29); wordsIndex.add(31);wordsIndex.add(0);
                wordsIndex.add(-14); wordsIndex.add(36); wordsIndex.add(38);wordsIndex.add(0);
                wordsIndex.add(-15); wordsIndex.add(43); wordsIndex.add(45);wordsIndex.add(0);
                wordsIndex.add(-16); wordsIndex.add(50); wordsIndex.add(52);wordsIndex.add(0);
                wordsIndex.add(-17); wordsIndex.add(57); wordsIndex.add(59);wordsIndex.add(0);
                wordsIndex.add(-18); wordsIndex.add(64); wordsIndex.add(66);wordsIndex.add(0);
                wordsIndex.add(-19); wordsIndex.add(71); wordsIndex.add(73);wordsIndex.add(0);
                wordsIndex.add(-20); wordsIndex.add(78); wordsIndex.add(80);wordsIndex.add(0);

                desc = "";
                for(int i=0; i<wordsIndex.size(); i++) {
                    switch (wordsIndex.get(i)) {
                        case 0 : desc += "\n"; break;
                        case -7 : desc += "العملة "; break;
                        case -8 : desc += "الشراء "; break;
                        case -9 : desc += "البيع "; break;
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
                    if(desc.charAt(i) !='.' && desc.charAt(i+1) =='0' && desc.charAt(i+2) =='0' && desc.charAt(i+3) ==' '){
                        desc = new StringBuilder(desc).insert(i+1, ".").toString();
                    }
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            txtDesc.setText(desc);
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
