<template>
	<div>
		<!-- Performances -->
		<Performance ref="performance" />
		<CreatePerformance ref="createperformance"/>

		<div v-if="loadedStage" class="flex justify-center pt-5 pb-10">
			<div v-if="loadedArtist" class="mx-20 w-2/3">
				<!-- Header -->
				<div class="flex">
					<h1 class="text-3xl font-bold">Upcoming Events</h1>
					<div class="flex flex-1 justify-end">
						<div class="w-3/12 text-center bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm mx-2 flex">
							<div class="my-auto mr-3">
								<search-icon size="1.0x" />
							</div>
							<input v-model="search" placeholder="Search for events" class="appearance-none focus:outline-none w-full">
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
							<option :key="artist.id" v-for="artist in artists">{{ artist.name }}</option>
						</select>
						<select class="w-2/12 text-center bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm mx-2">
							<option selected disabled>Select a stage</option>
							<option>None</option>
							<option :key="stage.id" v-for="stage in stages">{{ stage.name }}</option>
						</select>
					</div>
				</div>
				<!-- Body -->
				<div class="flex flex-row just mt-10">
					<div :key="performance.id" v-for="performance in performances" class="shadow rounded-lg bg-white w-1/3 hover:opacity-50 transition duration-200 cursor-pointer mr-5" @click="$refs.performance.visible = true, $refs.performance.artist = getSpecificArtist(performance), $refs.performance.performance = performance, $refs.performance.stage = getSpecificStage(performance)">
						<div class="h-48 rounded-t-lg" style="background-image: url('https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60'); background-position: center; background-size: cover;">
							<div class="flex p-4">
								<div class="mt-1">
									<span class="px-4 py-2 text-sm shadow bg-white rounded-lg font-bold">{{ performance.time }}</span>
								</div>
								<div class="flex flex-1 justify-end">
									<span class="px-4 py-2 text-sm shadow bg-white rounded-lg font-bold text-purple-600">{{ getSpecificStage(performance) }}</span>
								</div>
							</div>
						</div>
						<div class="flex py-10">
							<div class="ml-4 mr-8">
								<p class="text-sm text-purple-600">{{ getDate(performance).month }}</p>
								<p class="font-bold mt-3">{{ getDate(performance).day }}</p>
							</div>
							<div>
								<h1 class="font-medium mb-2">{{ getSpecificArtist(performance) }} - {{ performance.name }}</h1>
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
import performancesData from '@/performances.json'


import artistsAPI from '@/api/artists'
import stagesAPI from '@/api/stages'

export default {
	data(){
		return{
			artists: [],
			stages: [],
			performances: performancesData,
			search: '',
			loadedStage: false,
			loadedArtist: false
		}
	},
	components:{
		Performance,
		CreatePerformance,
		SearchIcon,
	},
	methods:{
		getDate(performance){
			const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
  				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
			];
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

		getSpecificArtist(performance){
			if(this.artists){
				return this.artists.find(element => element.id === performance.artist_id).name;
			} else{
				return 'undefined'
			}
		},
		getSpecificStage(performance){
			if(this.stages){
				return this.stages.find(element => element.id === performance.stage_id).name;
			} else{
				return 'undefined'
			}
		}
	},
	created(){
		artistsAPI.getArtists().then((response) => {
			this.artists = response.data
			this.loadedArtist = true;
		})
		stagesAPI.getStages().then((response) => {
			this.stages = response.data
			this.loadedStage = true;
		})
	},
}
</script>

<style scoped>
	#gradient{
		background: #D3CCE3;
		background-attachment: fixed;
		background-position: center;
		background-repeat: no-repeat;
		background-size: cover;
		background: -webkit-linear-gradient(90deg, #E9E4F0, rgb(253, 253, 255));
	}
</style>
