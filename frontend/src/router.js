import React from 'react';
import {Route} from 'react-router-dom';
import {Main} from './Components/Main';
import {ProductsList} from './Components/ProductsList';
import {ProductDetails} from './Components/ProductDetails';
import {ProductsAdd} from './Components/ProductsAdd';
import {ProducersAdd} from './Components/ProducersAdd';
import {ProducersList} from './Components/ProducersList';
import {CategoriesList} from './Components/CategoriesList';
import {CategoriesAdd} from './Components/CategoriesAdd';
import {CountriesList} from './Components/CountriesList';
import {CountriesAdd} from './Components/CountriesAdd';
import {PhotosList} from './Components/PhotosList';
import {PhotosAdd} from './Components/PhotosAdd';
import {ClientsList} from './Components/ClientsList';
import {ClientDetails} from './Components/ClientDetails';
import {ClientsAddressesList} from './Components/ClientsAddressesList';
import {ClientsOrdersList} from './Components/ClientsOrdersList';
import {AccountsAdd} from './Components/AccountsAdd';
import {AccountsList} from './Components/AccountsList';
import {DepotList} from './Components/DepotList';
import {DepotAdd} from './Components/DepotAdd';
import {OrderAdd} from './Components/OrderAdd';

export default (
    <React.Fragment>
        <Route exact path="/" component={Main} myname={"Main page"}/>
        <Route path="/products" component={ProductsList}/>
        <Route path="/products/:id" component={ProductDetails}/>
        <Route path="/products/add" component={ProductsAdd}/>
        <Route path="/producers" component={ProducersList}/>
        <Route path="/producers/add" component={ProducersAdd}/>
        <Route path="/categories" component={CategoriesList}/>
        <Route path="/categories/add" component={CategoriesAdd}/>
        <Route path="/countries" component={CountriesList}/>
        <Route path="/countries/add" component={CountriesAdd}/>
        <Route path="/photos" component={PhotosList}/>
        <Route path="/photos/add" component={PhotosAdd}/>
        <Route path="/accounts" component={AccountsList}/>
        <Route path="/accounts/add" component={AccountsAdd}/>
        <Route path="/clients" component={ClientsList}/>
        <Route path="/clients/:id" component={ClientDetails}/>
        <Route path="/clients/:id/addresses" component={ClientsAddressesList}/>
        <Route path="/clients/:id/orders" component={ClientsOrdersList}/>
        <Route path="/depot" component={DepotList}/>
        <Route path="/depot/add" component={DepotAdd}/>
        <Route path="/order/add" component={OrderAdd}/>
    </React.Fragment>
);