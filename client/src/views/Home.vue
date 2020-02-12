<template>
	<div>
		<!-- Artists -->
		<EditArtist ref="editartist"/>
		<CreateArtist ref="createartist"/>
		<DeleteArtist ref="deleteartist"/>

		<!-- Stages -->
		<EditStage ref="editstage"/>
		<CreateStage ref="createstage"/>
		<DeleteStage ref="deletestage"/>

		<!-- Performances -->
		<Performance ref="performance" />
		<CreatePerformance ref="createperformance"/>
		<div class="flex justify-center pt-5">
			<div class="w-2/3 rounded-lg flex items-center" style="height: 400px; background-image: url('https://images.unsplash.com/photo-1464375117522-1311d6a5b81f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2250&q=80'); background-repeat: no-repeat; background-position: center;">
				<div class="flex flex-1 justify-end mr-20">
					<h1 class="text-white text-3xl font-bold w-1/12 mr-20">Avans Schoolopdracht</h1>
				</div>
			</div>
		</div>

		<div class="flex justify-center pt-5 pb-10">
			<div class="mx-20 w-2/3">
				<!-- Header -->
				<div class="flex">
					<h1 class="text-3xl font-bold">Upcoming Events</h1>
					<div class="flex flex-1 justify-end">
						<select class="focus:outline-none px-5 text-sm rounded mx-5 border">
							<option>Weekdays</option>
						</select>
						<select class="focus:outline-none px-5 text-sm rounded mx-5 border">
							<option>Event type</option>
						</select>
						<select class="focus:outline-none px-5 text-sm rounded mx-5 border">
							<option>Any Category</option>
						</select>
					</div>
				</div>
				<!-- Body -->
				<div class="flex flex-row just mt-10">
					<div :key="artist.id" v-for="artist in getData().artists" class="shadow rounded-lg bg-white w-1/3 hover:opacity-50 transition duration-200 cursor-pointer mr-5" @click="$refs.performance.visible = true, $refs.performance.artist = artist, $refs.performance.performance = getPerformance(artist), $refs.performance.stage = getStage(artist)">
						<div class="h-48 rounded-t-lg" style="background-image: url('https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60'); background-position: center; background-size: cover;">
							<div class="flex p-4">
								<div class="mt-1">
									<span class="px-4 py-2 text-sm shadow bg-white rounded-lg font-bold">{{ getPerformance(artist).time }}</span>
								</div>
								<div class="flex flex-1 justify-end">
									<span class="px-4 py-2 text-sm shadow bg-white rounded-lg font-bold text-purple-600">{{ getStage(artist).name }}</span>
								</div>
							</div>
						</div>
						<div class="flex py-10">
							<div class="ml-4 mr-8">
								<p class="text-sm text-purple-600">{{ getDate(artist).month }}</p>
								<p class="font-bold mt-3">{{ getDate(artist).day }}</p>
							</div>
							<div>
								<h1 class="font-medium mb-2">{{ artist.name }} - {{ getPerformance(artist).name }}</h1>
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
import { ShareIcon } from 'vue-feather-icons'
import { HeartIcon } from 'vue-feather-icons'

// Artists
import Artist from '@/components/Artist/Artist'
import EditArtist from '@/components/Artist/EditArtist'
import CreateArtist from '@/components/Artist/CreateArtist'
import DeleteArtist from '@/components/Artist/DeleteArtist'

// Stages
import Stage from '@/components/Stage/Stage'
import EditStage from '@/components/Stage/EditStage'
import CreateStage from '@/components/Stage/CreateStage'
import DeleteStage from '@/components/Stage/DeleteStage'

// Performances
import Performance from '@/components/Performance/Performance'
import CreatePerformance from '@/components/Performance/CreatePerformance'

export default {
	components:{
		Artist,
		EditArtist,
		CreateArtist,
		DeleteArtist,
		Stage,
		EditStage,
		CreateStage,
		DeleteStage,
		Performance,
		CreatePerformance,
		ShareIcon,
		HeartIcon
	},
	methods:{
		getDate(artist){
			const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
  				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
			];
			var performance = this.getPerformance(artist);
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

		getPerformance(artist){
			return this.getData().performances.find(element => element.artist_id === artist.id)
		},

		getStage(artist){
			var performance = this.getPerformance(artist);
			return this.getData().stages.find(element => element.id === performance.stage_id)
		},
		getData(){
			return{
				artists:[
					{
						id: 1,
						name: 'Frans Bauer',
						desc: 'Amet quaerat porro aliquid tenetur reiciendis unde, repellendus voluptatem soluta animi accusamus neque voluptatibus laborum consectetur perspiciatis, sint nihil sequi est incidunt.'
					},
					{
						id: 2,
						name: 'Frans Shower',
						desc: 'Amet quaerat porro aliquid tenetur reiciendis unde, repellendus voluptatem soluta animi accusamus neque voluptatibus laborum consectetur perspiciatis, sint nihil sequi est incidunt.'
					}
				],
				stages:[
					{
						id: 1,
						name: 'Stage 1',
						desc: 'Amet quaerat porro aliquid tenetur reiciendis unde, repellendus voluptatem soluta animi accusamus neque voluptatibus laborum consectetur perspiciatis, sint nihil sequi est incidunt.'
					},
					{
						id: 2,
						name: 'Stage 2',
						desc: 'Amet quaerat porro aliquid tenetur reiciendis unde, repellendus voluptatem soluta animi accusamus neque voluptatibus laborum consectetur perspiciatis, sint nihil sequi est incidunt.'
					}
				],
				performances:[
					{
						id: 1,
						name: 'Optreden naam 1',
						time: '20:42',
						date: '2020-01-01',
						stage_id: 1,
						artist_id: 1,
					},
					{
						id: 2,
						name: 'Optreden naam 2',
						time: '22:11',
						date: '2020-02-14',
						stage_id: 2,
						artist_id: 2,
					}
				],
			}
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
