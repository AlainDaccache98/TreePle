<template>
<div>
  	<div class="topnav" id="myTopnav">
  <a href="#/home" class="active">Home</a>
  <a href="#/listtrees">List Trees</a>
  <a href="#/map">Map</a>
	<a href="#/planttree">Plant Tree</a>
	<a href="#/removetree">Remove Tree</a>
  <a href="#/updatetree">Update Tree</a>
	<a href="#/sustainability">Sustainability</a>
	<a href="#/forecast">Forecast</a>
  <a href="#/about">About</a>
    </div>
  <h2> Map</h2>
  <div id="map">
  <gmap-map
    :center="center"
    :zoom="15"
    style="width: 1000px; height: 500px"
  >
    <gmap-marker
      :key="index"
      v-for="(m, index) in markers"
      :position="m.position"
      :clickable="true"
      :draggable="true"
      @click="center=m.position"
    ></gmap-marker>
  </gmap-map>
  <button @click="check()">check</button>
  </div>
  <div id="scrollable">
    <table v-for="tree in trees" id = "table">
    <!-- not having id in dto!!!! -->
    <tr> 
    <td> {{tree.id}} </td>
    <td> {{tree.species}}</td>
    <td> ({{tree.location.latitude}}, {{tree.location.latitude}})</td>
    </tr>

    </table>
  </div>
</div>
</template>
<script src = './Map.js'>
</script>
<style>
  #map {
    margin-left: 20%; 
    margin-right: auto;
    float: left;
  }
  #scrollable{
    margin-left: 70%;
    margin-right: 15%;
    //background: black;
    overflow-y: scroll;
    height: 500px;
  }
  #table td{
    padding: 2px;
  }
  .topnav {
  overflow: hidden;
  background-color: #333;
}

.topnav a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

/*
.topnav p{
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}*/

.active {
  background-color: #4CAF50;
  color: white;
}

.topnav .icon {
  display: none;
}

.topnav a:hover {
  background-color: #555;
  color: white;
}
</style>


