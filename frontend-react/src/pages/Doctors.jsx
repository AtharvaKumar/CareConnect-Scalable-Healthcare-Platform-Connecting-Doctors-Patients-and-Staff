import React, { useEffect, useState } from 'react'
import axios from '../utils/axios'

export default function Doctors(){
  const [list, setList] = useState([])
  const [form, setForm] = useState({ doctorName:'', specialization:'', experienceYears:0 })

  const load = async () => {
    const { data } = await axios.get('/doctor/api/v1/doctors')
    setList(data)
  }
  useEffect(()=>{ load() }, [])

  const add = async (e) => {
    e.preventDefault()
    await axios.post('/doctor/api/v1/doctors', form)
    setForm({ doctorName:'', specialization:'', experienceYears:0 })
    load()
  }

  return (
    <div>
      <h2>Doctors</h2>
      <form onSubmit={add} style={{display:'grid', gap:6, maxWidth:420}}>
        <input placeholder="Name" value={form.doctorName} onChange={e=>setForm({...form, doctorName:e.target.value})}/>
        <input placeholder="Specialization" value={form.specialization} onChange={e=>setForm({...form, specialization:e.target.value})}/>
        <input placeholder="Experience Years" type="number" value={form.experienceYears} onChange={e=>setForm({...form, experienceYears:+e.target.value})}/>
        <button>Add</button>
      </form>
      <ul>
        {list.map(d=>(<li key={d.doctorId}>{d.doctorName} — {d.specialization} ({d.experienceYears} yrs)</li>))}
      </ul>
    </div>
  )
}
