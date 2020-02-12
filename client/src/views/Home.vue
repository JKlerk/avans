<template>
	<div>
		<!-- Performances -->
		<Performance ref="performance" />
		<CreatePerformance ref="createperformance"/>

		<div class="flex justify-center pt-5 pb-10">
			<div class="mx-20 w-2/3">
				<!-- Header -->
				<div class="flex">
					<h1 class="text-3xl font-bold">Upcoming Events</h1>
					<div class="flex flex-1 justify-end">
						<div class="w-3/12 text-center bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm mx-2 flex">
							<div class="my-auto mr-3">
								<search-icon size="1.0x" />
							</div>
							<input placeholder="Search for events" class="appearance-none focus:outline-none w-full">
						</div>
						<!-- TODO SORTING -->
						<div class="w-2/12 text-center bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm mx-2 flex">
							<label class="select-none" for="select">Filter by:</label>
							<div class="flex flex-1 justify-end mr-2">
								<select id="select" class="appearance-none focus:outline-none bg-white">
									<option>None</option>
									<option>Asc.</option>
									<option>Desc.</option>
								</select>
							</div>
						</div>
						<select class="w-2/12 text-center bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm mx-2">
							<option selected disabled>Select an artist</option>
							<option>None</option>
							<option :key="artist.id" v-for="artist in getArtists()">{{ artist.name }}</option>
						</select>
						<select class="w-2/12 text-center bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm mx-2">
							<option selected disabled>Select a stage</option>
							<option>None</option>
							<option :key="stage.id" v-for="stage in getStages()">{{ stage.name }}</option>
						</select>
					</div>
				</div>
				<!-- Body -->
				<div class="flex flex-row just mt-10">
					<div :key="artist.id" v-for="artist in getArtists()" class="shadow rounded-lg bg-white w-1/3 hover:opacity-50 transition duration-200 cursor-pointer mr-5" @click="$refs.performance.visible = true, $refs.performance.artist = artist, $refs.performance.performance = getSpecificPerformance(artist), $refs.performance.stage = getStage(artist)">
						<div class="h-48 rounded-t-lg" style="background-image: url('https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60'); background-position: center; background-size: cover;">
							<div class="flex p-4">
								<div class="mt-1">
									<span class="px-4 py-2 text-sm shadow bg-white rounded-lg font-bold">{{ getSpecificPerformance(artist).time }}</span>
								</div>
								<div class="flex flex-1 justify-end">
									<span class="px-4 py-2 text-sm shadow bg-white rounded-lg font-bold text-purple-600">{{ getSpecificStage(artist).name }}</span>
								</div>
							</div>
						</div>
						<div class="flex py-10">
							<div class="ml-4 mr-8">
								<p class="text-sm text-purple-600">{{ getDate(artist).month }}</p>
								<p class="font-bold mt-3">{{ getDate(artist).day }}</p>
							</div>
							<div>
								<h1 class="font-medium mb-2">{{ artist.name }} - {{ getSpecificPerformance(artist).name }}</h1>
								<p class="text-sm text-gray-700">Lorem ipsum dolor sit amet consectetur adipisicing elit. </p>
							</div>
						</div>
					</div>
					<div  @click="$refs.createperformance.visible = true" class="bg-white w-1/3 rounded-lg shadow h-auto flex items-center justify-center hover:bg-gray-100 cursor-pointer">
						<p class="text-6xl text-purple-600">+</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
// Icons
import { SearchIcon } from 'vue-feather-icons'

// Performances
import Performance from '@/components/Performance/Performance'
import CreatePerformance from '@/components/Performance/CreatePerformance'

// Json files
import artistsData from '@/artists.json'
import performancesData from '@/performances.json'
import stagesData from '@/stages.json'

export default {
	components:{
		Performance,
		CreatePerformance,
		SearchIcon,
	},
	methods:{
		getDate(artist){
			const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
  				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
			];
			var performance = this.getSpecificPerformance(artist);
			var fullDate = performance.date.split('-');
			let month = String
			let day = String

			// Removes 0 from month
			if(fullDate[1].includes(0)){
				month = monthNames[fullDate[1].split('0')[1] - 1]	
			} else{
				month = monthNames[fullDate[1] - 1]
			}

			// Removes 0 from day
			if(fullDate[2].includes(0)){
				day = fullDate[2].split('0')[1]	
			} else{
				day = fullDate[2]
			}

			return{ month: month, day: day }
		},	

		getSpecificPerformance(artist){
			return this.getPerformances().find(element => element.artist_id === artist.id)
		},

		getSpecificStage(artist){
			var performance = this.getSpecificPerformance(artist);
			return this.getStages().find(element => element.id === performance.stage_id)
		},
		getArtists(){
			return artistsData;
		},
		getStages(){
			return stagesData;
		},
		getPerformances(){
			return performancesData;
		},
	},
	created(){
		var d = new Date()
		var year = d.toISOString().split('T')
		var year2 = '2020-02-10T03:00 '.split('T')
		// console.log(year2)
		// var time = d.getHours() + ':' + d.getMinutes()
		// console.log(time)
		// console.log(year[0].split('-'))
	}
}
</script>

<style scoped>
	#gradient{
		background: #D3CCE3;
		/* background: -webkit-linear-gradient(-90deg, rgb(89, 0, 212), rgb(255, 94, 0)); */
		  background-attachment: fixed;
		background-position: center;
		background-repeat: no-repeat;
		background-size: cover;
		background: -webkit-linear-gradient(90deg, #E9E4F0, rgb(253, 253, 255));
		/* background: linear-gradient(45deg, #E9E4F0, #D3CCE3); */
	}
</style>
