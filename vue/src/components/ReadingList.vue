<template>
<div class="book-container">
    
<book-card v-bind:book="book" v-for="book in books" v-bind:key="book.book_id"/>
</div>
</template>

<script>
import BookCard from '../components/BookCard.vue'
import bookService from '../services/BookService.js'
export default {
    name: 'reading-list',
    components: {
        BookCard
    },
    data() {
    return {
      books: []
    };
  },
  created() {
    bookService.list()
    .then((response) => {
      this.books = response.data;
    });
  }

}
</script>

<style scoped>
.book-container {
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
    background-color: #f8fcfe;
  

    
}

button {
    width: 220px;
    background: linear-gradient(to right,#00afef,#ad63f1 );
    color: white;
    border-radius: 15px;
    border: none;
    outline: none;
    padding: 23px 0;
    margin-top: 10px;
    cursor: pointer;
    margin-left: 10px;
}

button:hover {
  background: linear-gradient(to right,#102770,#ad63f1 );
  color: #ffffff;
  box-shadow: 0 8px 24px 0 rgba(16,39,112,.2);
}

@media (max-width: 768px) {

  .book-container {
    display: grid;
    grid-template-columns: 1fr 1fr;
  }
}

</style>