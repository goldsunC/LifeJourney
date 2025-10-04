<template>
  <section class="section bg-white">
    <div class="container">
      <div class="max-w-3xl mx-auto text-center mb-16 fade-in">
        <h2 class="text-sm uppercase tracking-widest text-accent mb-4">{{ sectionSubtitle }}</h2>
        <h3 class="text-3xl font-bold mb-6">
          {{ sectionTitle }}
        </h3>
        <p class="text-gray-700">
          {{ sectionDescription }}
        </p>
      </div>
      
      <div class="studio-gallery">
        <div 
          v-for="(image, index) in images" 
          :key="index"
          class="studio-image fade-in"
        >
          <img :src="image.src" :alt="image.alt" class="w-full hover-zoom">
        </div>
      </div>
      
      <div class="max-w-3xl mx-auto mt-12 text-center fade-in">
        <p v-if="footerText" class="mb-6">
          {{ footerText }}
        </p>
        <a v-if="ctaLink && ctaText" :href="ctaLink" class="btn btn-outline">{{ ctaText }}</a>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'Photoalbum',
  props: {
    sectionTitle: {
      type: String,
      default: 'Studios & Work Environment'
    },
    sectionSubtitle: {
      type: String,
      default: 'Creative Spaces'
    },
    sectionDescription: {
      type: String,
      default: 'A glimpse into the spaces where Elena Mori\'s creative process unfolds.'
    },
    footerText: {
      type: String,
      default: 'Elena maintains two primary studios—one in Brooklyn, New York and another in Tokyo\'s artistic district. Both spaces are designed to foster creativity through natural light, open layouts, and dedicated zones for different artistic processes.'
    },
    ctaLink: {
      type: String,
      default: 'index.html#contact'
    },
    ctaText: {
      type: String,
      default: 'Inquire About Studio Visits'
    },
    images: {
      type: Array,
      default: function() {
        return [
          {
            src: 'https://picsum.photos/id/42/800/600',
            alt: 'Elena Mori\'s New York studio space'
          },
          {
            src: 'https://picsum.photos/id/28/800/600',
            alt: 'Painting area in Mori\'s studio'
          },
          {
            src: 'https://picsum.photos/id/174/800/600',
            alt: 'Digital workspace with monitors and drawing tablet'
          },
          {
            src: 'https://picsum.photos/id/163/800/600',
            alt: 'Tokyo studio with natural light'
          },
          {
            src: 'https://picsum.photos/id/116/800/600',
            alt: 'Sculpture workshop and material storage'
          },
          {
            src: 'https://picsum.photos/id/180/800/600',
            alt: 'Installation prototyping area'
          }
        ];
      }
    }
  },
  mounted() {
    // 添加滚动动画效果
    this.addScrollAnimation();
  },
  methods: {
    addScrollAnimation() {
      const fadeElements = document.querySelectorAll('.fade-in');
      
      const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            entry.target.style.opacity = '1';
            entry.target.style.transform = 'translateY(0)';
          }
        });
      }, {
        threshold: 0.1
      });
      
      fadeElements.forEach(el => {
        observer.observe(el);
      });
    }
  }
}
</script>

<style scoped>
/* 基础样式 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.container {
  width: 90%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

.section {
  padding: 6rem 0;
}

.text-accent {
  color: #D4AF37; /* 金色作为强调色 */
}

.bg-white {
  background-color: white;
}

.text-sm {
  font-size: 0.875rem;
}

.text-3xl {
  font-size: 2.5rem;
}

.font-bold {
  font-weight: 700;
}

.uppercase {
  text-transform: uppercase;
}

.tracking-widest {
  letter-spacing: 0.25em;
}

.mb-4 {
  margin-bottom: 1rem;
}

.mb-6 {
  margin-bottom: 1.5rem;
}

.mb-16 {
  margin-bottom: 4rem;
}

.mt-12 {
  margin-top: 3rem;
}

.max-w-3xl {
  max-width: 54rem;
}

.mx-auto {
  margin-left: auto;
  margin-right: auto;
}

.text-center {
  text-align: center;
}

.text-gray-700 {
  color: #374151;
}

.w-full {
  width: 100%;
}

/* 图片画廊样式 */
.studio-gallery {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 1.5rem;
  margin-top: 2rem;
}

.studio-image {
  height: 300px;
  overflow: hidden;
  border-radius: 2px;
}

.studio-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.7s ease;
}

.hover-zoom:hover {
  transform: scale(1.05);
}

/* 按钮样式 */
.btn {
  display: inline-block;
  padding: 0.75rem 2rem;
  text-transform: uppercase;
  letter-spacing: 0.25em;
  font-size: 0.875rem;
  font-weight: 500;
  transition: all 0.3s ease;
}

.btn-outline {
  border: 2px solid #121212;
  color: #121212;
}

.btn-outline:hover {
  background-color: #121212;
  color: white;
}

/* 动画效果 */
.fade-in {
  opacity: 0;
  transform: translateY(20px);
  transition: opacity 0.8s ease-out, transform 0.8s ease-out;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .section {
    padding: 4rem 0;
  }
  
  .studio-gallery {
    grid-template-columns: 1fr;
  }
  
  .studio-image {
    height: 250px;
  }
}
</style>