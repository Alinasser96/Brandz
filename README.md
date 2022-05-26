# Brandz Application
Android Kottlin Application that build in clean archticture and best practisies 
## summary
In this Application we have 2 screens
1. BrandsFragment:\
  that shows the brand's data including the items this brand offers
2. DetailsFragment:\
  that opens an item details including iimage slider
## Archticture
We follow the [onion architicture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)\
So we have 3 layers:
- **Domain layer**:
Would execute business logic which is independent of any layer and is just a pure kotlin package with no android specific dependency.

- **Data layer**:
Would dispense the required data for the application to the domain layer by implementing interface exposed by the domain

- **App layer**:  
Would include both domain and data layer and is android specific which executes the UI logic.

![arch](https://github.com/Alinasser96/Brandz/blob/master/pics/arch.jpg)

## Dependency Injection 
We use Dagger Hilt to apply dependency injection\
and heres the DAG [Dependency Acyclic Graph] for 2 flows that we have
|BrandsFragment|DetailsFragment|
|---|---|
|![dag1](https://github.com/Alinasser96/Brandz/blob/master/pics/dag-1.jpg) | ![dag2](https://github.com/Alinasser96/Brandz/blob/master/pics/dag-2.jpg)|

## Colors And Fonts
In this project we parse the color and Fonts from [developer json file](https://github.com/Alinasser96/Brandz/blob/master/app/src/main/assets/Developers.json)


## **Thanks a lot** ❤️
