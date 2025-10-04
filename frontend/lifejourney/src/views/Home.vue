<template>
  <div class="home">
    <!-- Timeline Section -->
    <section class="timeline-section">
      <div class="container">
        <!-- First Timeline Row -->
        <div class="timeline-row first-row">
          <div class="timeline-line"></div>
          <div class="timeline-events">
            <div 
              class="timeline-event" 
              v-for="(event, index) in firstTimelineEvents" 
              :key="event.id"
              :style="{ '--index': index }"
            >
              <div class="timeline-dot"></div>
              <div class="event-card" :class="{ 'reverse': event.reverse }">
                <div class="event-year" v-if="event.year">{{ event.year }}</div>
                <div class="event-content">
                  <div class="event-image">
                    <img :src="event.image" :alt="event.title" />
                  </div>
                  <div class="event-info">
                    <h3 class="event-title">{{ event.title }}</h3>
                    <p class="event-description">{{ event.description }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Second Timeline Row -->
        <div class="timeline-row second-row">
          <div class="timeline-line"></div>
          <div class="timeline-events">
            <div 
              class="timeline-event" 
              v-for="(event, index) in secondTimelineEvents" 
              :key="event.id"
              :style="{ '--index': index }"
            >
              <div class="timeline-dot"></div>
              <div class="event-card" :class="{ 'reverse': event.reverse }">
                <div class="event-year" v-if="event.year">{{ event.year }}</div>
                <div class="event-content">
                  <div class="event-image">
                    <img :src="event.image" :alt="event.title" />
                  </div>
                  <div class="event-info">
                    <h3 class="event-title">{{ event.title }}</h3>
                    <p class="event-description">{{ event.description }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Third Timeline Row -->
        <div class="timeline-row third-row">
          <div class="timeline-line"></div>
          <div class="timeline-events">
            <div 
              class="timeline-event" 
              v-for="(event, index) in thirdTimelineEvents" 
              :key="event.id"
              :style="{ '--index': index }"
            >
              <div class="timeline-dot"></div>
              <div class="event-card" :class="{ 'reverse': event.reverse }">
                <div class="event-year" v-if="event.year">{{ event.year }}</div>
                <div class="event-content">
                  <div class="event-image">
                    <img :src="event.image" :alt="event.title" />
                  </div>
                  <div class="event-info">
                    <h3 class="event-title">{{ event.title }}</h3>
                    <p class="event-description">{{ event.description }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

  </div>
</template>

<script setup>
import { ref } from 'vue'

// 第一行时间线事件数据
const firstTimelineEvents = ref([
  {
    id: 1,
    year: '2018',
    title: '首件简介',
    description: '首百页尚简界。工纶丝筒, 且后运会会议为不是客户分台。',
    image: 'https://images.unsplash.com/photo-1560472354-b33ff0c44a43?w=300&h=200&fit=crop',
    reverse: false
  },
  {
    id: 2,
    year: '2019',
    title: '商业讨论',
    description: '面色帽少的右力主勞的是此議氛十率有与盈宣',
    image: 'https://images.unsplash.com/photo-1554224155-6726b3ff858f?w=300&h=200&fit=crop',
    reverse: true
  },
  {
    id: 3,
    year: '2020',
    title: '公司发展',
    description: '前钓通倒逼公司选法戏了一与不展',
    image: 'https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?w=300&h=200&fit=crop',
    reverse: false
  },
  {
    id: 4,
    year: '2019',
    title: '传统与现代',
    description: '到有毒片檔润较省与原动代的機器',
    image: 'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=300&h=200&fit=crop',
    reverse: true
  }
])

// 第二行时间线事件数据
const secondTimelineEvents = ref([
  {
    id: 5,
    year: '2019',
    title: '项目交付',
    description: '庆德丙型运送有交会随记工作尚为子工塑',
    image: 'https://images.unsplash.com/photo-1497366216548-37526070297c?w=300&h=200&fit=crop',
    reverse: false
  },
  {
    id: 6,
    year: '2011',
    title: '商业决策',
    description: '有方退过集外,立枢性欲业想起票改划客',
    image: 'https://images.unsplash.com/photo-1497366811353-6870744d04b2?w=300&h=200&fit=crop',
    reverse: true
  },
  {
    id: 7,
    year: '2013',
    title: '设计理念',
    description: '险理念理圣是有设计设公我的的崇通作前工',
    image: 'https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=300&h=200&fit=crop',
    reverse: false
  },
  {
    id: 8,
    year: '2013',
    title: '旅行探索',
    description: '有运业的进乡退件的售套伯利容',
    image: 'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=300&h=200&fit=crop',
    reverse: true
  }
])

// 第三行时间线事件数据
const thirdTimelineEvents = ref([
  {
    id: 9,
    year: '2014',
    title: '团队合作',
    description: '市险企籀泥侠,思阿生存',
    image: 'https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=300&h=200&fit=crop',
    reverse: false
  },
  {
    id: 10,
    year: '2010',
    title: '会议回顾',
    description: '在旋砸的览介,更過程非活但想交屋的记展',
    image: 'https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?w=300&h=200&fit=crop',
    reverse: true
  },
  {
    id: 11,
    year: '2018',
    title: '创新项目',
    description: '创新思维引领新项目发展，开拓市场新领域',
    image: 'https://images.unsplash.com/photo-1488190211105-8b0e65b80b4e?w=300&h=200&fit=crop',
    reverse: false
  },
  {
    id: 12,
    year: '2021',
    title: '战略合作',
    description: '与行业领先企业达成战略合作，共同发展',
    image: 'https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=300&h=200&fit=crop',
    reverse: true
  }
])
</script>

<style scoped>
.home {
  padding-top: 70px;
  background: white;
  min-height: 100vh;
}


/* Timeline Section */
.timeline-section {
  padding: 30px 0 100px;
  background: white;
  position: relative;
}

/* 时间线行样式 */
.timeline-row.first-row {
  position: relative;
}

.timeline-row.second-row {
  position: relative;
}

.timeline-row {
  position: relative;
  margin-bottom: 100px;
  padding: 0 80px;
}

.timeline-row:last-child {
  margin-bottom: 0;
}

.timeline-line {
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  height: 2px;
  background: #d0d0d0;
  z-index: 1;
}

.timeline-events {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 2;
  gap: 40px;
}

.timeline-event {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  flex: 0 0 auto;
  max-width: 300px;
  min-width: 250px;
}

.timeline-dot {
  width: 16px;
  height: 16px;
  background: #666;
  border-radius: 50%;
  border: 4px solid white;
  box-shadow: 0 0 0 2px #d0d0d0;
  margin-bottom: 20px;
  z-index: 3;
}

.event-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  width: 100%;
}

.event-card.reverse {
  flex-direction: column-reverse;
}

.event-card.reverse .timeline-dot {
  margin-top: 20px;
  margin-bottom: 0;
}

.event-year {
  font-size: 0.9rem;
  font-weight: 600;
  color: #666;
  margin-bottom: 8px;
  background: #f5f5f5;
  padding: 4px 8px;
  border-radius: 12px;
  min-width: 40px;
}

.event-card.reverse .event-year {
  margin-top: 8px;
  margin-bottom: 0;
}

.event-content {
  background: white;
  border-radius: 8px;
  padding: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 260px;
  transition: all 0.3s ease;
  cursor: pointer;
}

.event-content:hover {
  transform: translateY(-8px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
}

.event-image {
  width: 100%;
  height: 180px;
  border-radius: 6px;
  overflow: hidden;
  margin-bottom: 8px;
  transition: all 0.3s ease;
}

.event-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: all 0.3s ease;
}

.event-content:hover .event-image {
  transform: scale(1.05);
}

.event-content:hover .event-image img {
  transform: scale(1.1);
}

.event-info {
  text-align: left;
}

.event-title {
  font-size: 0.9rem;
  font-weight: 600;
  color: #333;
  margin: 0 0 6px 0;
  line-height: 1.3;
}

.event-description {
  font-size: 0.8rem;
  color: #666;
  line-height: 1.4;
  margin: 0;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

/* Second row positioning */
.second-row .event-card {
  flex-direction: column-reverse;
}

.second-row .event-card:not(.reverse) {
  flex-direction: column-reverse;
}

.second-row .event-card.reverse {
  flex-direction: column;
}

.second-row .timeline-dot {
  margin-top: 20px;
  margin-bottom: 0;
}

.second-row .event-year {
  margin-top: 8px;
  margin-bottom: 0;
}

.second-row .event-card:not(.reverse) .event-year {
  margin-top: 8px;
  margin-bottom: 0;
}

.second-row .event-card.reverse .event-year {
  margin-top: 0;
  margin-bottom: 8px;
}

/* Third row positioning */
.third-row .event-card {
  flex-direction: column;
}

.third-row .event-card:not(.reverse) {
  flex-direction: column;
}

.third-row .event-card.reverse {
  flex-direction: column-reverse;
}

.third-row .timeline-dot {
  margin-bottom: 20px;
  margin-top: 0;
}

.third-row .event-year {
  margin-bottom: 8px;
  margin-top: 0;
}

.third-row .event-card:not(.reverse) .event-year {
  margin-bottom: 8px;
  margin-top: 0;
}

.third-row .event-card.reverse .event-year {
  margin-bottom: 0;
  margin-top: 8px;
}


/* Responsive Design */
@media (max-width: 1200px) {
  .timeline-row {
    padding: 0 50px;
    margin-bottom: 130px;
  }
  
  /* 响应式调整 */
  .timeline-events {
    align-items: center;
    gap: 40px;
  }
  
  .timeline-event {
    max-width: 200px;
    min-width: 180px;
  }
  
  .event-content {
    max-width: 170px;
  }
  
  .event-image {
    height: 100px;
  }
  
  .timeline-events {
    align-items: center;
    gap: 40px;
  }
}

@media (max-width: 768px) {
  .timeline-section {
    padding: 20px 0 80px;
  }
  
  .timeline-row {
    padding: 0 20px;
    margin-bottom: 70px;
  }
  

  
  .timeline-events {
    flex-direction: column;
    align-items: center;
    gap: 50px;
  }
  
  .timeline-line {
    display: none;
  }
  
  .timeline-event {
    max-width: 100%;
    width: 100%;
    min-width: auto;
  }
  
  .event-content {
    max-width: 420px;
    width: 100%;
  }
  
  .event-image {
    height: 220px;
  }
  
  .event-title {
    font-size: 1rem;
  }
  
  .event-description {
    font-size: 0.9rem;
  }
  
  .timeline-dot {
    display: none;
  }
}

@media (max-width: 480px) {
  .timeline-section {
    padding: 20px 0 60px;
  }
  
  .timeline-row {
    margin-bottom: 50px;
  }
  
  .event-content {
    max-width: 320px;
  }
  
  .event-image {
    height: 200px;
  }
  
  .timeline-events {
    gap: 40px;
  }
}
</style>
