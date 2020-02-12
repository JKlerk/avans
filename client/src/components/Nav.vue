<template>
		<div class="flex justify-center pt-5">
			<div class="w-2/3 rounded-lg flex items-center relative" style="height: 400px; background-image: url('https://images.unsplash.com/photo-1464375117522-1311d6a5b81f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2250&q=80'); background-repeat: no-repeat; background-position: center;">
				<div class="flex flex-1 justify-end mr-20">
					<h1 class="text-white text-3xl font-bold w-1/12 mr-20">Avans Schoolopdracht</h1>
				</div>
        <ul class="text-white absolute bottom-0 right-0 flex mb-5">
          <router-link class="mx-5 border-purple-500" :to="{ name: 'home' }"  active-class="border-b-2">Homepage</router-link>
          <router-link class="mx-5 border-purple-500" :to="{ name: 'artists' }"  active-class="border-b-2">Artists</router-link>
          <router-link class="mx-5 border-purple-500" :to="{ name: 'stages' }"  active-class="border-b-2">Stages</router-link>
        </ul>
			</div>
		</div>
</template>