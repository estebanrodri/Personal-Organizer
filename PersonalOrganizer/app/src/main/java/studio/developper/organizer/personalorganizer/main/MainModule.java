package studio.developper.organizer.personalorganizer.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


/**
 * Created by Esteban_R on 10/7/2016.
 */

public class MainModule {
    private MainView view;
    private String[] titles;
    private Fragment[] fragments;
    private FragmentManager fragmentManager;

    public MainModule(MainView view, FragmentManager fragmentManager, Fragment[] fragments, String[] titles) {
        this.view = view;
        this.titles = titles;
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
    }

    //@Provides
    //@Singleton
    //MainView providesMainView() {
      //  return this.view;
    //}

    //@Provides
    //@Singleton
    //MainPresenter providesMainPresenter(MainView view, EventBus eventBus, SessionInteractor sessionInteractor) {
        //return new MainPresenterImpl(view, eventBus, sessionInteractor);
    //}

    //@Provides
    //@Singleton
    //SessionInteractor providesSessionInteractor(MainRepository repository) {
        //return new SessionInteractorImpl(repository);
    //}

    //@Provides
    //@Singleton
    //MainRepository providesMainRepository(EventBus eventBus) {
        //return new MainRepositoryImpl(eventBus);
    //}

    //@Provides
    //@Singleton
    //MainSectionsPagerAdapter providesAdapter(FragmentManager fm, Fragment[] fragments, String[] titles) {
      //  return new MainSectionsPagerAdapter(fm, fragments, titles);
    //}

    //@Provides
    //@Singleton
  //  FragmentManager providesAdapterFragmentManager() {
    //    return this.fragmentManager;
    //}
//
    //@Provides
    //@Singleton
   //Fragment[] providesFragmentArrayForAdapter() {
       // return this.fragments;
    //}

    //@Provides
    //@Singleton
   // String[] providesStringArrayForAdapter() {
      //  return this.titles;
    //}
}
