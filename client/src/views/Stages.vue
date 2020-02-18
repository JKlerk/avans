<template>
    <div>
        <CreateStage ref="createstage" />
		<EditStage ref="editstage" />
        <div class="flex justify-center pt-5 pb-10">
            <div class="mx-20 w-2/3">
                <!-- Header -->
                <div class="flex">
                    <h1 class="text-3xl font-bold">Stages</h1>
                    <div class="flex flex-1 justify-end">
                        <div class="w-1/2 text-center bg-white block mt-2 rounded-lg border border-purple-200 focus:outline-none appearance-none px-2 py-2 leading-2 text-sm mx-2 flex">
                            <div class="my-auto mr-3">
                                <search-icon size="1.0x" />
                            </div>
                            <input placeholder="Search for stages" class="appearance-none focus:outline-none w-full">
                        </div>
                    </div>
                </div>
                <!-- Body -->
                <div class="flex flex-wrap mt-10">
                    <div class="flex w-1/2 hover:opacity-50 transition duration-200 cursor-pointer mb-5" @click="$refs.editstage.visible = true, $refs.editstage.stage = stage" :key="stage.id" v-for="stage in this.$store.state.stages">
                        <div class="rounded-l-lg w-1/3" style="background-image: url('https://images.unsplash.com/photo-1470225620780-dba8ba36b745?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60'); background-position: center; background-size: cover;"></div>
                        <div class="py-10 w-2/3 flex flex-col mr-5 bg-white shadow rounded-r-lg">
                            <h2 class="text-2xl ml-4 text-purple-700">{{ stage.name }}</h2>
                            <p class="text-gray-700 ml-4">{{ stage.description }}</p>
                        </div>
                    </div>
                    <div @click="$refs.createstage.visible = true" class="bg-white w-1/2 rounded-lg shadow h-auto flex items-center justify-center hover:bg-gray-100 cursor-pointer">
                        <p class="text-6xl text-purple-600">+</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { SearchIcon } from 'vue-feather-icons'
import CreateStage from '@/components/Stage/CreateStage'
import EditStage from '@/components/Stage/EditStage'
import api from '@/api/stages.js'

export default {
	components:{
		SearchIcon,
        CreateStage,
        EditStage,
	},
    created(){
		if(Object.keys(this.$store.state.stages).length === 0){
			api.getStages().then((response) => {
				this.$store.state.stages = response.data
			})
		}
	},
}
</script>